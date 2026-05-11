package com.krct;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.krct.cssv.Csvreader;
import pomcj.pompack.Pomtest;

public class LoginPage extends BaseTest {
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
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains(expectedMessage),
                "CSV Login failed for: " + username);
    }
    @Test(priority = 2)
    public void validLoginTest() {

        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        String message = driver.findElement(By.cssSelector("p")).getText();

        Assert.assertTrue(message.contains("Congratulations"),
                "Valid login failed");
    }
    @Test(priority = 3)
    public void wrongUsernameTest() {

        driver.get("https://wrongUser:admin@the-internet.herokuapp.com/basic_auth");

        driver.navigate().refresh();

        Assert.assertTrue(driver.getCurrentUrl().contains("basic_auth"),
                "Wrong username handling failed");
    }
    @Test(priority = 4)
    public void wrongPasswordTest() {

        driver.get("https://admin:wrongPass@the-internet.herokuapp.com/basic_auth");

        driver.navigate().refresh();

        Assert.assertTrue(driver.getCurrentUrl().contains("basic_auth"),
                "Wrong password handling failed");
    }
    @Test(priority = 5)
    public void logoutTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.cssSelector("a.button.secondary.radius")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "Logout failed - User not redirected to login page");
        Assert.assertTrue(driver.findElement(By.id("username")).isDisplayed(),
                "Login page not displayed after logout");
    }
}