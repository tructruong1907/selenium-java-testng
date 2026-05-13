package webdriver;

import javaSDET.javaBasic.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

import static java.lang.Thread.sleep;


public class Topic_05_part03_Textbox_TextArea {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
       // driver = new ChromeDriver();
       driver = new FirefoxDriver();

    }

    @Test //gắn chỉ dẫn
    public void TC_01_TechPanda() {
        driver.get("http://live.techpanda.org/");
        String firstName;
        String lastName;
        String fullName;
        String emailAddress;
        String password;

        firstName = "Truc";
        lastName = "Truong";
        fullName = firstName + " " + lastName;
        emailAddress = firstName + "." + lastName + new Random().nextInt(9999) + "gmail.com";
        password = "123456";

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);


        driver.findElement(By.xpath("//button[@title='Register']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.success-msg span")));
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");

        String contacInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contacInfo.contains(fullName));
        Assert.assertTrue(contacInfo.contains(emailAddress));

        driver.findElement(By.cssSelector("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        driver.findElement(By.cssSelector("//a[text()='Add Your Review']")).click();
        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("good product\n good price");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("good product\n good price");
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(firstName);
        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Your review has been accepted for moderation.");

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
