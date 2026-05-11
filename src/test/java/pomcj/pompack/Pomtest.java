package pomcj.pompack;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pomtest {

private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");

public Pomtest(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
}
  public void enterUsername(String username) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
        .sendKeys(username);
}
public void enterPassword(String password) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField))
        .sendKeys(password);
}
public void clickLogin() {
    wait.until(ExpectedConditions.elementToBeClickable(loginButton))
        .click();
}
public String getMessage() {
    String text = wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage))
        .getText();
    return text.replace("×", "").trim();
}
}

