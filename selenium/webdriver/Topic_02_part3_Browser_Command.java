package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_02_part3_Browser_Command {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
    }


    @Test //gắn chỉ dẫn
    public void TC_01_(){
       //tương tác với Browser - qua driver
        driver.get("");

        //tương tác vs Element - qia findElement

    }
    @Test
    public void TC_02_(){

    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
