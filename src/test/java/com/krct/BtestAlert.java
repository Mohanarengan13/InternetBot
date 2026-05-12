package com.krct;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BtestAlert extends BaseTest {
    By alertBtn = By.xpath("//button[text()='Click for JS Alert']");
    By confirmBtn = By.xpath("//button[text()='Click for JS Confirm']");
    By promptBtn = By.xpath("//button[text()='Click for JS Prompt']");
    By result = By.id("result");
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
}