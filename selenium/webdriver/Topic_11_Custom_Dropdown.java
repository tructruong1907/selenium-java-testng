package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;


public class Topic_11_Custom_Dropdown {
        WebDriver driver;
        WebDriverWait explicitWait;
        Select select;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();
        //driver.get("https://demo.nopcommerce.com/");
       explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    //Viết hàm dùng chung cho các combo box (hàm tái sử dụng)
    public void SelectItemCombobox(String parentLocator, String childLocator, String itemValue){
        driver.findElement(By.cssSelector(parentLocator)).click();

        List<WebElement> childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        for (WebElement item : childItems){
            String itemText = item.getText();
            System.out.println(itemText);
            if(item.getText().equals(itemValue)){
                item.click();
                break;
            }
        }

    }

    public void SelectItemComboboxAll(By parentLocator, By childLocator, String itemValue){
        driver.findElement(parentLocator).click();

        List<WebElement> childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childLocator));
        for (WebElement item : childItems){
            String itemText = item.getText();
            System.out.println(itemText);
            if(item.getText().equals(itemValue)){
                item.click();
                break;
            }
        }

    }

    public void EditItemCombobox(String parentLocator, String childLocator, String itemValue){
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(itemValue);

        List<WebElement> childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        for (WebElement item : childItems){
            String itemText = item.getText();
            System.out.println(itemText);
            if(item.getText().equals(itemValue)){
                item.click();
                break;
            }
        }

    }

    @Test //gắn chỉ dẫn
    public void TC_01_JQuery(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        //Chọn text
        SelectItemCombobox("span#speed-button","ul#speed-menu div","Medium"); //Select Speed
        // Verify text đã chọn
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");

        SelectItemCombobox("span#files-button","ul#files-menu div","Some unknown file"); //Select a file
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(), "Some unknown file");

        SelectItemCombobox("span#number-button","ul#number-menu div","2"); //Select number
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "2");

        SelectItemCombobox("span#salutation-button","ul#salutation-menu div","Mrs."); //Select title
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");

        }
    @Test
    public void TC_02_ReactJS() throws InterruptedException{
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        SelectItemCombobox("","","");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException{
       driver.get("https://mikerodham.github.io/vue-dropdowns/");
       SelectItemCombobox("div.btn-group","ul.dropdown-menu a","Second Option");
       sleep(3000);
       Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
    }

    @Test
    public void TC_04_Editable() throws InterruptedException{
       driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

       SelectItemCombobox("input.search","div.item","Andorra");
       sleep(3000);
       Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Andorra");

       //editable
        EditItemCombobox("input.search","div.item","Benin");
        sleep(4000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");


    }
    @Test
    public void TC_05_Honda() throws InterruptedException{

       driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
       sleep(5000);
       select = new Select(driver.findElement(By.cssSelector("select.province")));


        //Custom combobox
        SelectItemCombobox("button#selectize-input","div.choose-car div.dropdown-menu>a","CITY G (Đen)");
        Assert.assertEquals(driver.findElement(By.cssSelector("button.btn.dropdown-toggle")).getText(), "CITY G (Đen)");

        //Default Combobox
        SelectItemCombobox("select.province","select.province>option","Đồng Nai");
        sleep(3000);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Đồng Nai");

    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
