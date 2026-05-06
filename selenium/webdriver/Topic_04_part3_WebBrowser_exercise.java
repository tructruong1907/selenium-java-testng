package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_04_part3_WebBrowser_exercise {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();
    }

    @Test //gắn chỉ dẫn
    public void TC_01_Url(){
       driver.get("http://live.techpanda.org/");
       driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");

    }
    @Test
    public void TC_02_Title(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test
    public void TC_03_Navigate(){
       driver.get("http://live.techpanda.org/");
       driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
       driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
       Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
       driver.navigate().back();
       Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
       driver.navigate().forward();
       Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }
    @Test
    public void TC_04_getPageSource(){
       driver.get("http://live.techpanda.org/");
       driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
       Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
       driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }


    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
