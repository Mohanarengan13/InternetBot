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
public class FileUploadTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority = 1)
    public void verifyPage() {

        driver.get("https://the-internet.herokuapp.com/upload");

        wait.until(ExpectedConditions.titleContains("The Internet"));

        String title = driver.getTitle();

        System.out.println("Page Title : " + title);

        Assert.assertEquals(title, "The Internet");

        Assert.assertTrue(
                driver.findElement(By.id("file-upload")).isDisplayed()
        );

        System.out.println("Upload page verified successfully");
    }

    @Test(priority = 2)
    public void fileUpload() {

        driver.get("https://the-internet.herokuapp.com/upload");

        String filePath = "C:\\Users\\acer\\Downloads\\image.jpg";

        WebElement upload =
                driver.findElement(By.id("file-upload"));

        upload.sendKeys(filePath);

        driver.findElement(By.id("file-submit")).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("uploaded-files"))
        );

        String uploadedFileName =
                driver.findElement(By.id("uploaded-files")).getText();

        System.out.println("Uploaded File : " + uploadedFileName);

        Assert.assertEquals(uploadedFileName, "image.jpg");

        System.out.println("File uploaded successfully");
    }

    @Test(priority = 3)
    public void unsupportedFileUpload() {

        driver.get("https://the-internet.herokuapp.com/upload");

        String filePath = "C:\\Users\\acer\\Downloads\\image.jpg";

        driver.findElement(By.id("file-upload"))
                .sendKeys(filePath);

        driver.findElement(By.id("file-submit")).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("uploaded-files"))
        );

        String uploaded =
                driver.findElement(By.id("uploaded-files")).getText();

        System.out.println("Uploaded File : " + uploaded);

        Assert.assertEquals(uploaded, "image.jpg");

        System.out.println("Unsupported file upload test passed");
    }

    @Test(priority = 4)
    public void downloadPageCheck() {

        driver.get("https://the-internet.herokuapp.com/download");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("example"))
        );

        Assert.assertTrue(
                driver.findElement(By.className("example")).isDisplayed()
        );

        System.out.println("Download page verified successfully");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }
    }
}