package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {

    @Test(priority = 1)
    public void verifyUploadPageUI() {
        driver.get("https://the-internet.herokuapp.com/upload");

        Assert.assertTrue(driver.getTitle().contains("The Internet"));
        Assert.assertTrue(driver.findElement(By.id("file-upload")).isDisplayed());
    }

    @Test(priority = 2)
    public void fileUploadTest() {
        driver.get("https://the-internet.herokuapp.com/upload");

        String filePath = "C:\\Downloads\\image.jpg";

        WebElement upload = driver.findElement(By.id("file-upload"));
        upload.sendKeys(filePath);

        driver.findElement(By.id("file-submit")).click();

        String uploadedFileName =
                driver.findElement(By.id("uploaded-files")).getText();

        Assert.assertEquals(uploadedFileName, "image.jpg");
    }

    @Test(priority = 3)
    public void unsupportedFileUploadTest() {
        driver.get("https://the-internet.herokuapp.com/upload");

        String filePath = "C:\\Downloads\\image.jpg";

        driver.findElement(By.id("file-upload")).sendKeys(filePath);
        driver.findElement(By.id("file-submit")).click();

        String uploaded =
                driver.findElement(By.id("uploaded-files")).getText();

        Assert.assertEquals(uploaded, "image.jpg");
    }

    @Test(priority = 4)
    public void downloadPageCheck() {
        driver.get("https://the-internet.herokuapp.com/download");

        Assert.assertTrue(driver.findElement(By.className("example")).isDisplayed());
    }
}