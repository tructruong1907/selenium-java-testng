package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class Topic_12_button {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        //driver = new ChromeDriver();
       driver = new FirefoxDriver();
        //driver.get("https://demo.nopcommerce.com/");
    }

    //execute : viết test case
    //cú pháp HTML: Tagname (thẻ) - Attribute (thuộc tính) - Value (giá trị thuộc tính)
    //cú pháp XPath: //tagname[@attribute = 'value']
    //cú pháp CSS:  tagname[attribute = 'value']

    @Test //gắn chỉ dẫn
    public void TC_01_Fahasa_1(){
       driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
       //verify button text
        Assert.assertEquals(driver.findElement(By.cssSelector("button.fhs-btn-login span")).getText(),"Đăng nhập");
    }
    @Test
    public void TC_01_Fahasa_2(){
       driver.get("https://www.fahasa.com/customer/account/create");

       driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
       //verify button disable
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

    }

    @Test
    public void TC_01_Fahasa_3() throws InterruptedException{
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        //verify background color
        By loginButton = By.cssSelector("button.fhs-btn-login");
        String loginBtnColor = driver.findElement(loginButton).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginBtnColor).asHex().toUpperCase(),"#000000");
        sleep(3000);

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0908122345");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
        sleep(3000);

        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        loginBtnColor = driver.findElement(loginButton).getCssValue("background-color");
        System.out.println(loginBtnColor);
        Assert.assertEquals(Color.fromString(loginBtnColor).asHex().toUpperCase(),"#C92127");


    }

    @Test //gắn chỉ dẫn
    public void TC_02_Huawei_buttonText() throws InterruptedException{
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");

        sleep(3000);

        By registerBtn = By.cssSelector("div.hwid-btn-primary");

        //verify button text
        Assert.assertEquals(driver.findElement(registerBtn).getText(),"REGISTER");

        //verify enabled
        Assert.assertTrue( driver.findElement(registerBtn).isEnabled());

        //verify bg color
        String registerbgbtn =  driver.findElement(registerBtn).getCssValue("background-color");
        System.out.println(registerbgbtn);
        System.out.println(Color.fromString(registerbgbtn).asHex());
        System.out.println(Color.fromString(registerbgbtn).asHex().toUpperCase());

        Assert.assertEquals(Color.fromString(registerbgbtn).asHex(),"#007dff");

    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
