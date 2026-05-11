package com.krct;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
    public class AlertPage {
        WebDriver driver;
        WebDriverWait wait;
        By alertBtn = By.xpath("//button[text()='Click for JS Alert']");
        By confirmBtn = By.xpath("//button[text()='Click for JS Confirm']");
        By promptBtn = By.xpath("//button[text()='Click for JS Prompt']");
        By result = By.id("result");
        @BeforeMethod
        public void setup() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
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
            String okResult = driver.findElement(result).getText();
            Assert.assertEquals(okResult, "You clicked: Ok");
            driver.findElement(confirmBtn).click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
            String cancelResult = driver.findElement(result).getText();
            Assert.assertEquals(cancelResult, "You clicked: Cancel");
        }
        @Test(priority = 3)
        public void jsPromptTest() {
            openPage();
            driver.findElement(promptBtn).click();
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.sendKeys("mohan");
            alert.accept();
            String okResult = driver.findElement(result).getText();
            Assert.assertEquals(okResult, "You entered: mohan");
            driver.findElement(promptBtn).click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
            String cancelResult = driver.findElement(result).getText();
            Assert.assertEquals(cancelResult, "You entered: null");
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
        public void teardown() {
            driver.quit();
        }
    }

