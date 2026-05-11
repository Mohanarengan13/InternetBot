package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class HoverTest extends BaseTest {

    @Test(priority = 1)
    public void hoverTest() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement second = driver.findElements(By.className("figure")).get(1);
        Actions actions = new Actions(driver);
        actions.moveToElement(second).perform();
        WebElement caption = second.findElement(By.className("figcaption"));
        wait.until(ExpectedConditions.visibilityOf(caption));
        Assert.assertTrue(caption.isDisplayed());
    }
    @Test(priority = 2)
    public void checkboxTest() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> boxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        WebElement c1 = boxes.get(0);
        WebElement c2 = boxes.get(1);

        boolean s1 = c1.isSelected();
        c1.click();
        Assert.assertNotEquals(c1.isSelected(), s1);
        c1.click();
        Assert.assertEquals(c1.isSelected(), s1);

        boolean s2 = c2.isSelected();
        c2.click();
        Assert.assertNotEquals(c2.isSelected(), s2);
        c2.click();
        Assert.assertEquals(c2.isSelected(), s2);
    }
    @Test(priority = 3)
    public void dropDownTest() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);
        List<WebElement> options = dropdown.getOptions();
        Assert.assertEquals(options.size(), 3);
        for (int i = 1; i < options.size(); i++) {
            String value = options.get(i).getText();
            dropdown.selectByVisibleText(value);
            Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), value);
        }
    }
}