package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicPage extends BaseTest {

    @Test(priority = 1)
    public void dynamicControls() {

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        driver.findElement(By.xpath("//button[text()='Remove']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("message"), "It's gone"
        ));

        Assert.assertTrue(
                driver.findElement(By.id("message"))
                        .getText()
                        .contains("It's gone")
        );

        driver.findElement(By.xpath("//button[text()='Enable']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("message"), "enabled"
        ));

        Assert.assertTrue(
                driver.findElement(By.id("message"))
                        .getText()
                        .contains("enabled")
        );
    }

    @Test(priority = 2)
    public void dynamicLoad() {

        driver.get("https://the-internet.herokuapp.com/dynamic_loading");

        driver.findElement(
                By.linkText("Example 1: Element on page that is hidden")
        ).click();

        driver.findElement(By.xpath("//button[text()='Start']")).click();

        WebElement hello = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("finish")
                )
        );

        Assert.assertEquals(
                hello.getText().trim(),
                "Hello World!"
        );
    }

    @Test(priority = 3)
    public void disappearingElementReappearsTest() {

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebElement checkbox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("checkbox")
                )
        );

        Assert.assertTrue(checkbox.isDisplayed());

        driver.navigate().refresh();

        WebElement checkboxAfterRefresh = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("checkbox")
                )
        );
        Assert.assertTrue(checkboxAfterRefresh.isDisplayed());
    }
}