package com;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.krct.Csvreader;
import com.pompackage.pomcj.Pomtest;

public class LoginPage extends BaseTest{

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        System.out.println(getClass().getClassLoader().getResource("loginData.csv"));
        return Csvreader.readCSV(
                getClass().getClassLoader().getResource("loginData.csv").getPath()
        );
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedMessage) {

        Pomtest loginPage = new Pomtest(driver, wait);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String msg = loginPage.getMessage();

        Assert.assertTrue(msg.contains(expectedMessage),
                "Test failed for: " + username + " / " + password);
    }
}