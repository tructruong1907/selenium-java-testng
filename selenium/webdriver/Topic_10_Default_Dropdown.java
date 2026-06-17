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

import static java.lang.Thread.sleep;

import java.time.Duration;
import java.util.List;


public class Topic_10_Default_Dropdown {
        WebDriver driver;
        Select select;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();
        //driver.get("https://demo.nopcommerce.com/");
    }

    @Test //gắn chỉ dẫn
    public void TC_01_egov_site(){
        driver.get("https://egov.danang.gov.vn/reg");
        //Default Drppdown: thẻ select + option - có hàm hỗ trợ trong selenium
        //khai báo biến cho dropdown List, sử dụng các hàm: SelectByIndex/SelectByValue/SelectByVisibleText

        //Khởi tạo biến select gắn với element là Tỉnh Thành
        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));

        //lấy ra số lượng item trong dropdown list ***
        int tinhThanhNumber = select.getOptions().size();
        Assert.assertEquals(tinhThanhNumber,67);
        System.out.println("tinh thanh number: " + tinhThanhNumber);

        //kiểm tra dropdown list là single hay là multiple ***
        Assert.assertFalse(select.isMultiple());

        //Index: dễ bị thay đổi nên chạy dễ bị lỗi khi thêm/xóa/sửa item
        //Khó nhớ - index này tương ứng với item nào
        //Khi testcase chạy bị lỗi và cần reproduce lại thì data test cần giả lập khó nhớ
        //select.deselectByIndex(4); //bỏ chọn
        //select.selectByIndex(4); //chọn

        //Value: ít thay đổi
        //Value không phải là thuộc tính cố định/bắt buộc của dropdown
        //Đôi khi dev thiết kế không có value -> không chọn được dropdown
        //Có value thì dữ liệu khó nhớ
        //select.selectByValue("9806"); //chọn
        //select.deselectByValue("9806"); //bỏ chọn

        //Text: bị thay đổi text thì tương ứng giá trị sẽ được cập nhật -> lỗi
        //Nếu thêm/xóa/sửa dữ liệu dễ cập nhật/ít lỗi
        //Khi testcase chạy bị lỗi và cần reproduce lại thì dữ liệu cần giả lập dễ tìm/dễ nhớ/có nghĩa
        select.selectByVisibleText("thành phố Hồ Chí Minh"); //chọn ***
        //select.deselectByVisibleText("thành phố Hồ Chí Minh"); //bỏ chọn

        //verify dữ liệu đã chọn thành công ***
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"thành phố Hồ Chí Minh");



    }
    @Test
    public void TC_02_rode_site() throws InterruptedException{
       driver.get("https://rode.com/en-int/support/where-to-buy");

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("select#country")));;

       new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
       driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("Ho Chi Minh");
       driver.findElement(By.xpath("//button[text()='Search']")).click();
       sleep(3000);

       List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));
       for(WebElement dealer: dealers){
           System.out.println(dealer.getText());
       }

    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
