package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicPage {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void dynamicControlsTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        driver.findElement(By.xpath("//button[text()='Remove']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("message"), "It's gone"));

        Assert.assertTrue(
                driver.findElement(By.id("message")).getText().contains("It's gone")
        );

        driver.findElement(By.xpath("//button[text()='Enable']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("message"), "enabled"));

        Assert.assertTrue(
                driver.findElement(By.id("message")).getText().contains("enabled")
        );
    }

    @Test(priority = 2)
    public void dynamicLoadingTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading");

        driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();

        driver.findElement(By.xpath("//button[text()='Start']")).click();

        WebElement hello = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
        );

        Assert.assertEquals(
                hello.getText().trim(),
                "Hello World!"
        );
    }

    @Test(priority = 3)
    public void disappearingElementReappearsTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebElement checkbox = driver.findElement(By.id("checkbox"));
        Assert.assertTrue(checkbox.isDisplayed());

        driver.navigate().refresh();

        WebElement checkboxAfterRefresh = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("checkbox"))
        );

        Assert.assertTrue(checkboxAfterRefresh.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}