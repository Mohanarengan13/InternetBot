package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.krct.cssv.Csvreader;
import pomcj.pompack.Pomtest;

public class AloginPage extends BaseTest {
    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return Csvreader.readCSV(
                getClass().getClassLoader()
                        .getResource("loginData.csv")
                        .getPath()
        );
    }
    @Test(priority = 1, dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedMessage) {
        driver.get("https://the-internet.herokuapp.com/login");
        Pomtest loginPage = new Pomtest(driver, wait);
        loginPage.enterName(username);
        loginPage.enterPass(password);
        loginPage.clickLogin();
        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains(expectedMessage),
                "CSV Login failed for: " + username);
    }

    @Test(priority = 2)
    public void validLogin() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        String message = driver.findElement(By.cssSelector("p")).getText();
        Assert.assertTrue(message.contains("Congratulations! You must have the proper credentials."));
    }
    @Test(priority = 3)
    public void wrongUser() {
        driver.get("https://wrongUser:admin@the-internet.herokuapp.com/basic_auth");
        driver.navigate().refresh();
        Assert.assertTrue(driver.getCurrentUrl().contains("basic_auth"));
    }
    @Test(priority = 4)
    public void wrongPass() {
        driver.get("https://admin:wrongPass@the-internet.herokuapp.com/basic_auth");
        driver.navigate().refresh();
        Assert.assertTrue(driver.getCurrentUrl().contains("basic_auth"));
    }
    @Test(priority = 5)
    public void logoutTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                .sendKeys("tomsmith");
        driver.findElement(By.id("password"))
                .sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.button.secondary.radius")
        )).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}