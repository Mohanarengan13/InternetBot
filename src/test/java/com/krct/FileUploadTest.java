package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FileUploadTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void verifyPage() {

        driver.get("https://the-internet.herokuapp.com/upload");

        Assert.assertTrue(driver.getTitle().contains("The Internet"));

        Assert.assertTrue(
                driver.findElement(By.id("file-upload")).isDisplayed()
        );
    }

    @Test(priority = 2)
    public void fileUpload() {

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
    public void unsupportedFileUpload() {

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

        Assert.assertTrue(
                driver.findElement(By.className("example")).isDisplayed()
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}