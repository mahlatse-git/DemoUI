package inspiredtestingui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavaScriptAlerts {
    WebDriver driver;
    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void openChrome(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    public void AcceptAlert(){
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
        jsAlert.click();
        WebElement resultText = driver.findElement(By.id("result"));
        Assert.assertEquals(resultText.getText(),"You successfully clicked an alert");
        driver.switchTo().alert().accept();
    }

    @Test
    public void DismissAlert(){
        WebElement jsConfirm = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        jsConfirm.click();

        WebElement resultText = driver.findElement(By.id("result"));
        Assert.assertEquals(resultText.getText(),"You clicked: Ok");
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void EnterTextInPrompt(){
        WebElement jsPrompt = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));
        jsPrompt.click();
        driver.switchTo().alert().sendKeys("Mahlatse");
        driver.switchTo().alert().accept();

        WebElement resultText = driver.findElement(By.id("result"));
        Assert.assertEquals(resultText.getText(),"You entered: Mahlatse");
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
