package com;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class HoverTest extends BaseTest {

    @Test
    public void hoverTest() {

        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement second = driver.findElements(By.className("figure")).get(1);

        Actions actions = new Actions(driver);
        actions.moveToElement(second).perform();

        WebElement caption = second.findElement(By.className("figcaption"));
        wait.until(ExpectedConditions.visibilityOf(caption));

        System.out.println("Hover successful, caption displayed");
    }
    @Test
    public void testDragAndDrop() throws InterruptedException{
        driver.get("https://demo.automationtesting.in/Static.html");
        List<WebElement> elements =driver.findElements(By.cssSelector("#dragarea div"));
        WebElement target=driver.findElement(By.id("droparea"));
        for (WebElement element:elements){
            Actions actions=new Actions(driver);
            actions.dragAndDrop(element, target).perform();
        }
         List<WebElement> droppedElements =driver.findElements(By.cssSelector("#droparea img"));
         Assert.assertEquals(elements.size(),droppedElements.size());
       // Thread.sleep(2000);
    }
    @Test
    public void jsAlertTest() throws InterruptedException{
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement element=driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        element.click();
       Alert alert=wait.until(ExpectedConditions.alertIsPresent());
       //Thread.sleep(2000);
       Assert.assertEquals(alert.getText(),"I am a JS Alert");
       alert.accept();
    }
    @Test
    public void ConfirmOkTest(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
          WebElement element=driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        element.click();
        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        WebElement result=driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(),"You clicked: Ok");
         element.click();
        alert=wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        result=driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(),"You clicked: Cancel");
    }
     @Test
    public void jsPromptTest() throws InterruptedException{
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
          WebElement element=driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        element.click();
        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("mohan");
        alert.accept();
        WebElement result=driver.findElement(By.id("result"));
        Thread.sleep(2000);
        Assert.assertEquals(result.getText(),"You entered: mohan");
         element.click();
        alert=wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        result=driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(),"You entered: null");
    }
    }