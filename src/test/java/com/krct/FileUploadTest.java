package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {

    @Test(priority = 1)
    public void verifyPage() {

        driver.get("https://the-internet.herokuapp.com/upload");

        wait.until(ExpectedConditions.titleContains("The Internet"));

        Assert.assertEquals(driver.getTitle(), "The Internet");

        Assert.assertTrue(driver.findElement(By.id("file-upload")).isDisplayed());
    }

    @Test(priority = 2)
    public void fileUpload() {

        driver.get("https://the-internet.herokuapp.com/upload");

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/image.jpg";

        WebElement upload = driver.findElement(By.id("file-upload"));
        upload.sendKeys(filePath);

        driver.findElement(By.id("file-submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));

        Assert.assertEquals(
                driver.findElement(By.id("uploaded-files")).getText(),
                "image.jpg"
        );
    }

    @Test(priority = 3)
    public void unsupportedFileUpload() {

        driver.get("https://the-internet.herokuapp.com/upload");

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/image.jpg";

        driver.findElement(By.id("file-upload")).sendKeys(filePath);
        driver.findElement(By.id("file-submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));

        Assert.assertEquals(
                driver.findElement(By.id("uploaded-files")).getText(),
                "image.jpg"
        );
    }

    @Test(priority = 4)
    public void downloadPageCheck() {

        driver.get("https://the-internet.herokuapp.com/download");

        Assert.assertTrue(
                driver.findElement(By.className("example")).isDisplayed()
        );
    }
}