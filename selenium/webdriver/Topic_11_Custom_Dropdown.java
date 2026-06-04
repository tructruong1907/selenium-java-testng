package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class Topic_11_Custom_Dropdown {
        WebDriver driver;
        Select select;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();
        //driver.get("https://demo.nopcommerce.com/");
    }

    @Test //gắn chỉ dẫn
    public void TC_01_(){

    }
    @Test
    public void TC_02_() throws InterruptedException{

    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
