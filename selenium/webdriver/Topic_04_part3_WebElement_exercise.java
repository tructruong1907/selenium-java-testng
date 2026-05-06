package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;


public class Topic_04_part3_WebElement_exercise {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //execute : viết test case
    //cú pháp HTML: Tagname (thẻ) - Attribute (thuộc tính) - Value (giá trị thuộc tính)
    //cú pháp XPath: //tagname[@attribute = 'value']
    //cú pháp CSS:  tagname[attribute = 'value']

    @Test
    public void TC_01_Displayed(){
       //isDisplayed() - kiểm tra bất kỳ 1 element nào hiển thị (có thể nhìn thấy được và có kích thước cụ thể)
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //khai báo biến
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
        WebElement ageUnder18Radio =  driver.findElement(By.cssSelector("input#under_18"));
        WebElement educationTextbox =  driver.findElement(By.xpath("//textarea[@id='edu']"));
        WebElement name05Hover =  driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        //email Textbox
        if(emailTextbox.isDisplayed()){
            System.out.println("Email textbox is Displayed");
            emailTextbox.sendKeys("Automation Testing");
        }
        else{
            System.out.println("Email textbox is Not Displayed");
        }

        //age Under 18 Radio
        if(ageUnder18Radio.isDisplayed()){
            System.out.println("Age Under 18 Radio is Displayed");
            ageUnder18Radio.click();
        }
        else{
            System.out.println("Age Under 18 Radio is Not Displayed");
        }

        //education Textbox
        if(educationTextbox.isDisplayed()){
            System.out.println("Education textbox is Displayed");
            educationTextbox.sendKeys("Automation Testing");

        }
        else{
            System.out.println("Education textbox is Not Displayed");
        }

        //hover name 05
        if(name05Hover.isDisplayed()){
            System.out.println("Name 05 Hover is Displayed");
            name05Hover.click();
        }
        else{
            System.out.println("Name 05 Hover is Not Displayed");
        }

    }

    @Test //gắn chỉ dẫn
    public void TC_02_Enabled(){
       //isEnabled() - kiểm tra bất kỳ element nào có thể tương tác lên được
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
        WebElement ageUnder18Radio =  driver.findElement(By.cssSelector("input#under_18"));
        WebElement passwordTextbox =  driver.findElement(By.cssSelector("input#disable_password"));
        WebElement biographyTextarea =  driver.findElement(By.cssSelector("textarea#bio"));

        //email Textbox
        if(emailTextbox.isEnabled()){
            System.out.println("Email textbox is Enabled");
        }
        else{
            System.out.println("Email textbox is Not Enabled");
        }

        //age Radio button
        if(ageUnder18Radio.isEnabled()){
            System.out.println("Age Under 18 Radio is Enabled");
        }
        else{
            System.out.println("Age Under 18 Radio is Not Enabled");
        }

        //password Textbox is disabled
        if(passwordTextbox.isEnabled()){
            System.out.println("Password textbox is Enabled");
        }
        else{
            System.out.println("Password textbox is Not Enabled");
        }

        //Bio Graphy Textbox
        if(biographyTextarea.isEnabled()){
            System.out.println("Biography textbox is Enabled");
        }
        else{
            System.out.println("Biography textbox is Not Enabled");
        }
        }


    @Test //gắn chỉ dẫn
    public void TC_03_Selected() throws InterruptedException{
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement ageUnder18Radio =  driver.findElement(By.cssSelector("input#under_18"));
        WebElement javaCheckbox = driver.findElement(By.cssSelector("input#java"));

        ageUnder18Radio.click();
        javaCheckbox.click();
        sleep(3000);

        if(ageUnder18Radio.isSelected()){
            System.out.println("Age Under 18 Radio is Selected");
        }
        else{
            System.out.println("Age Under 18 Radio is Not Selected");
        }
        if(javaCheckbox.isSelected()){
            System.out.println("Java Checkbox is Selected");
        }
        else{
            System.out.println("Java Checkbox is Not Selected");
        }


        driver.findElement(By.cssSelector("input#over_18")).click();
        javaCheckbox.click();
        sleep(3000);

        if(ageUnder18Radio.isSelected()){
            System.out.println("Age Under 18 Radio is Selected");
        }
        else{
            System.out.println("Age Under 18 Radio is Not Selected");
        }
        if(javaCheckbox.isSelected()){
            System.out.println("Java Checkbox is Selected");
        }
        else{
            System.out.println("Java Checkbox is Not Selected");
        }
    }

    @Test //gắn chỉ dẫn
    public void TC_04_MailChimp_Register_Validate () throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        WebElement emailCheckbox =  driver.findElement(By.cssSelector("input#email"));
        emailCheckbox.sendKeys("cecilia-ttruc@gmail.com");

        //Op 1 : tự động điền username bằng cách sử dụng tab
        emailCheckbox.sendKeys(Keys.TAB);

        //Op 2: tự động điền username bằng cách click vào user name textbox
        //WebElement usernameCheckbox = driver.findElement(By.cssSelector("input#new_username"));
        //usernameCheckbox.click();
        sleep(3000);

        //validate password - only lowercase
        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
        passwordTextbox.sendKeys("a");
        passwordTextbox.sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());
        sleep(3000);
    }

    @Test
    public void TC_05_Login_Empty_Email_Pass() throws InterruptedException {
       driver =  new ChromeDriver();
       driver.get("http://live.techpanda.org/");

       driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
       driver.findElement(By.xpath("//button[@title='Login']")).click();
       Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
       Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");
       sleep(3000);

    }

    @Test
    public void TC_06_Login_Invalid_Email() throws InterruptedException {
        driver =  new ChromeDriver();
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("12341234@1234.123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
        sleep(3000);

    }
    @Test
    public void TC_07_Login_PassUnder6char() throws InterruptedException {
        driver =  new ChromeDriver();
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("tructruong@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12345");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
        sleep(3000);

    }

    @Test
    public void TC_08_Login_Incorrect_Email_Pass() throws InterruptedException {
        driver =  new ChromeDriver();
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("tructruong@gmaill.12.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456789");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");
        sleep(3000);

    }



    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
