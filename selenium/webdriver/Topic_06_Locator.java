package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class Topic_06_Locator {
        WebDriver driver;

   @BeforeClass //gắn chỉ dẫn
        //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/login");
    }

    //execute : viết test case
    //cú pháp HTML: Tagname (thẻ) - Attribute (thuộc tính) - Value (giá trị thuộc tính)
    //cú pháp XPath: //tagname[@attribute = 'value']
    //cú pháp CSS:  tagname[attribute = 'value']

    @Test //gắn chỉ dẫn
    public void TC_01_id() throws InterruptedException {
       //tìm element theo id - nhập giá trị vào ô textbox
       driver.findElement(By.id("small-searchterms")).sendKeys("Truc Truong");

       //cho dừng màn hình lại trong 3s (1s = 1000ms)
        sleep( 3000);

        driver.findElement(By.id("Email")).sendKeys("cecilia.ttruc@gmail.com");
        sleep(3000);

        //ở đây còn lỗi, cần sửa lại
        driver.findElement(By.id("password")).sendKeys("Truc@123");
        sleep(3000);
    }
    @Test
    public void TC_02_Class() throws InterruptedException{
       //lưu ý: nếu có khoảng trắng -> chỉ lấy 1 phần (là phần duy nhất) - nếu lấy luôn khoảng trắng nó sẽ bị lỗi
        // nếu không có khoảng trắng thì lấy hết

        driver.findElement(By.className("login-button")).click();
        sleep(3000);

    }

    @Test
    public void TC_03_Name() throws InterruptedException{
       //khác với class, nếu name có khoảng trắng thì nó vẫn hiểu -> lấy hết
        driver.findElement(By.name("Email"));
        sleep(3000);

    }

    @Test
    public void TC_04_LinkText(){
       //chỉ làm việc vs element là link và có text
       //có targname = a và thuộc tính = href
        //lấy hết toàn bộ text (tuyệt đối)
        driver.findElement(By.linkText("Forgot password?"));

    }
    @Test
    public void TC_05_Partial_Link_Text() throws InterruptedException{
       //chỉ làm việc vs element là link và có text
        // có thể lấy hết hoặc 1 phần text (tương đối)
        driver.findElement(By.partialLinkText("Digital downloads"));
        sleep(3000);

        driver.findElement(By.partialLinkText("Cards"));
        sleep(3000);

    }
    @Test
    public void TC_06_Tagname(){
       //Tên thẻ(HTML)
        //Tìm tất cả các element giống nhau (thẻ của component giống nhau)
        //Vd: tìm tất cả các thẻ liên quan đến button/textbox/checkbox....
        driver.findElement(By.tagName("button"));
    }

    @Test
    public void TC_07_Css(){
       //Css có thể cover các kiểu findElement từ 1->6
        //Css có thể hoặc không cover được xpath
        //cover id
        driver.findElement(By.cssSelector("input#Email")); //cách 1: viết tắt
        driver.findElement(By.cssSelector("#Email")); //cách 2
        driver.findElement(By.cssSelector("input[id='Email']")); //cách 3: viết chuẩn trong css tagname[attribute = 'value']

        //cover class
        driver.findElement(By.cssSelector("button.login-button"));
        driver.findElement(By.cssSelector("button[class='button-1 login-button']"));

        //cover name
        driver.findElement(By.cssSelector("input[name='Email']"));

        //cover linkText
        driver.findElement(By.cssSelector("a[href='/passwordrecovery']"));

        //cover partialLinkText
        driver.findElement(By.cssSelector("a[href*='download']"));

        //cover tagname
        driver.findElement(By.cssSelector("button"));

    }

    @Test
    public void TC_08_XPath(){
       //XPath có thể cover được tất cả các kiểu findElement từ 1->7
        //chỉ có thể viết theo dạng chuẩn //tagname[@attribute = 'value']

        //cover id
        driver.findElement(By.xpath("//input[@id='Email']"));

        //cover class
        driver.findElement(By.xpath("//button[@class='button-1 login-button']")); //tìm tuyệt đối
        driver.findElement(By.xpath("//button[contains(@class,'login-button')]")); //tìm 1 phần


        //cover name
        driver.findElement(By.xpath("//input[@name='q']"));


        //cover linkText
        driver.findElement(By.xpath("//a[text()='Forgot password?']"));


        //cover partialLinkText
        driver.findElement(By.xpath("//a[contains(text(),'password?')]"));

        //cover tagname
        driver.findElement(By.xpath("//button"));


    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}
