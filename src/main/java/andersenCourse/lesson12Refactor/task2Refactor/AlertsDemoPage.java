package andersenCourse.lesson12Refactor.task2Refactor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsDemoPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String link = "https://www.hyrtutorials.com/p/alertsdemo.html";

    public AlertsDemoPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "alertBox")
    private WebElement alertBoxButton;
    @FindBy(id = "confirmBox")
    private WebElement confirmBoxButton;
    @FindBy(id = "promptBox")
    private WebElement promptBoxButton;
    @FindBy(id = "output")
    private WebElement outputField;
    @FindBy(xpath = "//p[@class='fc-button-label' and text()='Consent']")
    private WebElement cookieAcceptor;

    public void performButtonsAndDisplayOutput() {
        wait.until(ExpectedConditions.visibilityOf(cookieAcceptor)).click();
        alertBoxButton.click();
        alertAcceptor();
        getOutputText();
        confirmBoxButton.click();
        alertDecliner();
        getOutputText();
        promptBoxButton.click();
        sendKeysToAndAcceptAlert();
        getOutputText();
    }

    private void alertAcceptor() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    private void alertDecliner() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    private void sendKeysToAndAcceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys("Final step of this task");
        driver.switchTo().alert().accept();
    }

    private void getOutputText() {
        System.out.println(wait.until(ExpectedConditions.visibilityOf(outputField)).getText());
    }

    public String getLink() {
        return link;
    }
}
