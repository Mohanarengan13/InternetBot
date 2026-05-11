package com.krct;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class BtestAlert extends BaseTest{

    private WebDriver driver;
    private WebDriverWait wait;

    By alertBtn = By.xpath("//button[text()='Click for JS Alert']");
    By confirmBtn = By.xpath("//button[text()='Click for JS Confirm']");
    By promptBtn = By.xpath("//button[text()='Click for JS Prompt']");
    By result = By.id("result");

    @BeforeMethod
    public void setup() {

        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);

        }

        else if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        }

        else {

            throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test(priority = 1)
    public void jsAlertTest() {

        openPage();

        driver.findElement(alertBtn).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();
    }

    @Test(priority = 2)
    public void jsConfirmTest() {

        openPage();

        driver.findElement(confirmBtn).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        Assert.assertEquals(driver.findElement(result).getText(), "You clicked: Ok");

        driver.findElement(confirmBtn).click();

        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();

        Assert.assertEquals(driver.findElement(result).getText(), "You clicked: Cancel");
    }

    @Test(priority = 3)
    public void jsPromptTest() {

        openPage();

        driver.findElement(promptBtn).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("mohan");
        alert.accept();

        Assert.assertEquals(driver.findElement(result).getText(), "You entered: mohan");

        driver.findElement(promptBtn).click();

        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();

        Assert.assertEquals(driver.findElement(result).getText(), "You entered: null");
    }

    @Test(priority = 4)
    public void resultUpdateTest() {

        openPage();

        driver.findElement(alertBtn).click();
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(result).getText().contains("alert"));

        driver.findElement(confirmBtn).click();
        driver.switchTo().alert().dismiss();
        Assert.assertTrue(driver.findElement(result).getText().contains("Cancel"));

        driver.findElement(promptBtn).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("test");
        alert.accept();

        Assert.assertTrue(driver.findElement(result).getText().contains("test"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}