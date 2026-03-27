package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;


public class Topic_09_CSS_XPath {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/books/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    //execute : viết test case
    //cú pháp HTML: Tagname (thẻ) - Attribute (thuộc tính) - Value (giá trị thuộc tính)
    //cú pháp XPath: //tagname[@attribute = 'value']
    //cú pháp CSS:  tagname[attribute = 'value']

    @Test //gắn chỉ dẫn
    public void TC_01_Use_Parent() throws InterruptedException {
       //driver.findElement(By.xpath("//article[@data-productid='37']//button[@class='button-2 product-box-add-to-cart-button']")).click();
       driver.findElement(By.xpath("//article[@data-productid='37']//button[contains(@class,'product-box-add')]")).click();
       sleep(3000);

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
