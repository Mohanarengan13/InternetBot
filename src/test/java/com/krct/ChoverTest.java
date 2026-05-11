package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ChoverTest extends BaseTest{

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");


        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void hoverTest() {

        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement first =
                driver.findElements(By.className("figure")).get(1);

        Actions actions = new Actions(driver);

        actions.moveToElement(first).perform();

        WebElement caption =
                first.findElement(By.className("figcaption"));

        wait.until(ExpectedConditions.visibilityOf(caption));

        Assert.assertTrue(caption.isDisplayed());
    }

    @Test(priority = 2)
    public void checkboxTest() {

        driver.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> checkbox =
                driver.findElements(By.cssSelector("input[type='checkbox']"));

        WebElement checkbox1 = checkbox.get(0);
        WebElement checkbox2 = checkbox.get(1);

        boolean status1 = checkbox1.isSelected();

        checkbox1.click();

        Assert.assertNotEquals(checkbox1.isSelected(), status1);

        checkbox1.click();

        Assert.assertEquals(checkbox1.isSelected(), status1);

        boolean status2 = checkbox2.isSelected();

        checkbox2.click();

        Assert.assertNotEquals(checkbox2.isSelected(), status2);

        checkbox2.click();

        Assert.assertEquals(checkbox2.isSelected(), status2);
    }

    @Test(priority = 3)
    public void dropDownTest() {

        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement ddElement =
                driver.findElement(By.id("dropdown"));

        Select dropdown = new Select(ddElement);

        List<WebElement> options = dropdown.getOptions();

        Assert.assertEquals(options.size(), 3);

        for (int i = 1; i < options.size(); i++) {

            String value = options.get(i).getText();

            dropdown.selectByVisibleText(value);

            Assert.assertEquals(
                    dropdown.getFirstSelectedOption().getText(),
                    value
            );
        }
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}