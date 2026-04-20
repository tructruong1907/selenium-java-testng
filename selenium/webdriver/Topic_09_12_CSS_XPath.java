package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;


public class Topic_09_12_CSS_XPath {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();

    }

    //execute : viết test case
    //cú pháp HTML: Tagname (thẻ) - Attribute (thuộc tính) - Value (giá trị thuộc tính)
    //cú pháp XPath: //tagname[@attribute = 'value']
    //cú pháp CSS:  tagname[attribute = 'value']

    @Test //gắn chỉ dẫn
    public void TC_01_XPath_Use_Parent() throws InterruptedException {
       //driver.findElement(By.xpath("//article[@data-productid='37']//button[@class='button-2 product-box-add-to-cart-button']")).click();
       driver.findElement(By.xpath("//article[@data-productid='37']//button[contains(@class,'product-box-add')]")).click();
       sleep(3000);

    }
    @Test
    public void TC_02_CSS_ID(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       //Xpath //input[@id='email']
        //CSS
        driver.findElement(By.cssSelector("input[id='email']")).sendKeys("tructruong@gmail.com"); //cách viết 1: tagname[attribute='value']
        driver.findElement(By.cssSelector("input#email")).sendKeys("tructruong@gmail.com"); //cách viết 2: tagname#value
        driver.findElement(By.cssSelector("#email")); //cách viết 3 #value

    }
    @Test
    public void TC_03_CSS_Class(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //Xpath //ul[@class='from-list']
        //CSS
        driver.findElement(By.cssSelector("ul[class='from-list']")); //cách viết 1: tagname[attribute='value']
        driver.findElement(By.cssSelector("ul.from-list")); //cách viết 2: tagname.valude
        driver.findElement(By.cssSelector(".from-list")); //cách viết 3: .value

        //Trường hợp ngoại lệ: nếu value của class CÓ KHOẢNG TRẮNG -> có thể lấy toàn bộ hoặc 1 phần
        driver.findElement(By.cssSelector("div[class='col-2 registered-users']")).getText(); //lấy toàn bộ theo cú pháp chuẩn
        driver.findElement(By.cssSelector("div.registered-users")); //lấy 1 phần theo cách viết 2
        driver.findElement(By.cssSelector("div.col-2.registered-users")); //lấy toàn phần theo cách viết 2 - thay khoảng trắng bằng dấu .
        //hoặc
        driver.findElement(By.cssSelector("div.registered-users.col-2")); //value giữa các khoảng trắng có thể đảo hoặc giữ nguyên
    }
    @Test
    public void TC_04_CSS_AND(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //Xpath //input[@id='email' and @name='login[username]']
        //CSS
        driver.findElement(By.cssSelector("input[id='email'][name='login[username]']")).sendKeys("tructruong@gmail.com");
    }
    @Test
    public void TC_05_CSS_OR(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //Xpath //input[@id='email' or @id='pass']
        //CSS
        driver.findElement(By.cssSelector("input[id='email'],[id='pass']")).sendKeys("tructruong@gmail.com");
    }
    @Test
    public void TC_06_CSS_NOT(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //Xpath //input[not(@id='email')]
        //CSS
        driver.findElement(By.cssSelector("input:not([id='email'])")).sendKeys("tructruong@gmail.com"); //tagname:not([attribute='value'])
        driver.findElement(By.cssSelector("input:not([#email])"));
    }


    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
