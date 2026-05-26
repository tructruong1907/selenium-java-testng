package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import static java.lang.Thread.sleep;


public class Topic_05_part03_Textbox_TextArea {
        WebDriver driver;
        By loadingIcon = By.cssSelector("div.oxd-loading-spinner");
        String firstName;
        String lastName;
        String fullName;
        String emailAddress;
        String password, userName, passportNumber, passportComment;
        String reviewProduct;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
       //driver = new ChromeDriver();
       driver = new FirefoxDriver();

       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

       firstName = "Truc";
       lastName = "Truong";
       fullName = firstName + " " + lastName;
       emailAddress = firstName + "." + lastName + new Random().nextInt(9999) + "@gmail.com";
       userName = firstName + "." + lastName + new Random().nextInt(9999);
       password = "123456@Tt";
       reviewProduct = "good product\n good price";
       passportNumber = "CD-123456";
       passportComment = "CD123456\nNguyen Thi Minh Khai\n phuong Xuan Hoa\n Tp.HCM";
   }

    @Test //gắn chỉ dẫn
    public void TC_01_TechPanda() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);


        driver.findElement(By.xpath("//button[@title='Register']")).click();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.success-msg span")));
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");

        String contacInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contacInfo.contains(fullName));
        Assert.assertTrue(contacInfo.contains(emailAddress));

        //Verify Edit trong màn hình My Account
        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getDomAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getDomAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getDomAttribute("value"), emailAddress);

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys(reviewProduct);
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys(reviewProduct);
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(firstName);
        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Your review has been accepted for moderation.");

    }
    //TC này có xử lý wait để xử lý Loading page
    @Test
    public void TC_02_OrangeHRM() throws InterruptedException{
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();

        //driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        //or click vào Add button
        driver.findElement(By.xpath("//button[contains(string(),'Add')]")).click();
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));


        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
        //get EmployeeID
        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println("Employee ID: " + employeeID);

        //bật 'Create Login Details'
        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label")).click();


        //điền thông tin Username, Password, Confirm password
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        System.out.println("user name: " + userName);
        System.out.println("password: " + password);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();


        //Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("p.oxd-text--toast-message")))));
        sleep(4000);
        //Verify Text Save successfully
        Assert.assertEquals(driver.findElement(By.cssSelector("p.oxd-text--toast-message")).getText(),"Successfully Saved");
        //Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-toast-content--success')]/p[text()='Successfully Saved']")).isDisplayed());
        //Loading Icon 1 - Add new
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));
        //Loading Icon 2 - Personal Details
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Verify data in "Personal Detail" screen
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"), employeeID);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]")).click();

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportComment);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        //Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("p.oxd-text--toast-message")))));
        sleep(3000);
        //Verify Text Save successfully
        Assert.assertEquals(driver.findElement(By.cssSelector("p.oxd-text--toast-message")).getText(),"Successfully Saved");
        //Loading Icon
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Verify passport
        driver.findElement(By.xpath("//div[text()='" + passportNumber + "']/parent::div/following-sibling::div//i[contains(@class,'bi-pencil-fill')]")).click();

        sleep(3000);
        //Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getDomProperty("value"), passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getDomProperty("value"), passportComment);

        //Logout
        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //Login bẳng user ở step 5
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //click My Info
        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        sleep(3000);

        //Verify thông tin đúng
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getDomProperty("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"), employeeID);


    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
