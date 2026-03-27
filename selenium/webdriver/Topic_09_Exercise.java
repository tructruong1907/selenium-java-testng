package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic_09_Exercise {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
    //Arrange
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test //gắn chỉ dẫn
    public void TC_01_RegisterWithEmptyData(){
     driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    //Action
     driver.findElement(By.xpath("//button[@type='submit']")).click();
     //Assert
     Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
     Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
     Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
     Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
     Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
     Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");

    }
    @Test
    public void TC_02_Register_Invalid_Email(){
     driver.get("https://alada.vn/tai-khoan/dang-ky.html");
     driver.findElement(By.id("txtFirstname")).sendKeys("Truc Truong");
     driver.findElement(By.id("txtEmail")).sendKeys("TrucTruong@@123");
     driver.findElement(By.id("txtCEmail")).sendKeys("TrucTruong@@123");
     driver.findElement(By.name("txtPassword")).sendKeys("12345678");
     driver.findElement(By.name("txtCPassword")).sendKeys("12345678");
     driver.findElement(By.id("txtPhone")).sendKeys("0901234567");

     driver.findElement(By.xpath("//button[@type='submit']")).click();

     Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
     Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập email hợp lệ");

    }
    @Test
    public void TC_03_Register_Incorrect_Confirm_Email(){
     driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    driver.findElement(By.id("txtFirstname")).sendKeys("Truc Truong");
    driver.findElement(By.id("txtEmail")).sendKeys("tructruong@123");
    driver.findElement(By.id("txtCEmail")).sendKeys("tructruong@1233");
    driver.findElement(By.name("txtPassword")).sendKeys("12345678");
    driver.findElement(By.name("txtCPassword")).sendKeys("12345678");
    driver.findElement(By.id("txtPhone")).sendKeys("0901234567");

    driver.findElement(By.xpath("//button[@type='submit']")).click();

    Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }
    @Test
    public void TC_04_Register_Invalid_Parword(){
     driver.get("https://alada.vn/tai-khoan/dang-ky.html");
     driver.findElement(By.id("txtFirstname")).sendKeys("Truc Truong");
     driver.findElement(By.id("txtEmail")).sendKeys("tructruong@123");
     driver.findElement(By.id("txtCEmail")).sendKeys("tructruong@123");
     driver.findElement(By.name("txtPassword")).sendKeys("12345");
     driver.findElement(By.name("txtCPassword")).sendKeys("12345");
     driver.findElement(By.id("txtPhone")).sendKeys("0901234567");

     driver.findElement(By.xpath("//button[@type='submit']")).click();

     Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
     Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");


    }
    @Test
    public void TC_05_Register_Invalid_confirm_Password(){
     driver.get("https://alada.vn/tai-khoan/dang-ky.html");
     driver.findElement(By.id("txtFirstname")).sendKeys("Truc Truong");
     driver.findElement(By.id("txtEmail")).sendKeys("tructruong@123");
     driver.findElement(By.id("txtCEmail")).sendKeys("tructruong@123");
     driver.findElement(By.name("txtPassword")).sendKeys("123456");
     driver.findElement(By.name("txtCPassword")).sendKeys("1234566");
     driver.findElement(By.id("txtPhone")).sendKeys("0901234567");

     driver.findElement(By.xpath("//button[@type='submit']")).click();

     Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");

    }
    @Test
    public void TC_06_Register_Invalid_Phone_Number(){
     driver.get("https://alada.vn/tai-khoan/dang-ky.html");
     driver.findElement(By.id("txtFirstname")).sendKeys("Truc Truong");
     driver.findElement(By.id("txtEmail")).sendKeys("tructruong@123");
     driver.findElement(By.id("txtCEmail")).sendKeys("tructruong@123");
     driver.findElement(By.name("txtPassword")).sendKeys("123456");
     driver.findElement(By.name("txtCPassword")).sendKeys("123456");
     // Phone number <10
     driver.findElement(By.id("txtPhone")).sendKeys("090123456");

     driver.findElement(By.xpath("//button[@type='submit']")).click();

     Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

     //Phone Number>11
     driver.findElement(By.id("txtPhone")).clear();
     driver.findElement(By.id("txtPhone")).sendKeys("090123456789");

     driver.findElement(By.xpath("//button[@type='submit']")).click();

     Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

     //Phone Number is wrong prefix number
     driver.findElement(By.id("txtPhone")).clear();
     driver.findElement(By.id("txtPhone")).sendKeys("0151234567");

     driver.findElement(By.xpath("//button[@type='submit']")).click();

     Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
