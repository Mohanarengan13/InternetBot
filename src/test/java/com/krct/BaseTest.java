package com.krct;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        String browser = ConfigReader.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);

        }

        else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();

        }

        else {

            throw new RuntimeException(
                    "Unsupported browser: " + browser
            );
        }

        driver.manage().window().maximize();

        int timeout = ConfigReader.getTimeout();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(timeout));

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(timeout)
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }
    }

    public static WebDriver getDriver() {

        return driver;
    }
}