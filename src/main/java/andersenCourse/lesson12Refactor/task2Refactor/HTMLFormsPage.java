package andersenCourse.lesson12Refactor.task2Refactor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HTMLFormsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String link = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit";

    public HTMLFormsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='accept-choices']")
    private WebElement cookieAcceptor;

    @FindBy(xpath = "//input[@id='fname']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='lname']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class, 'w3-panel')]")
    private WebElement noteField;

    public HTMLFormsPage fillData() {
        wait.until(ExpectedConditions.visibilityOf(cookieAcceptor)).click();
        frameSwitcher();
        firstNameField.clear();
        firstNameField.sendKeys("Andrej");
        lastNameField.clear();
        lastNameField.sendKeys("Novak");
        submitButton.click();
        return this;
    }

    private void frameSwitcher() {
        driver.switchTo().frame("iframeResult");
    }

    public HTMLFormsPage getNoteFieldText() {
        System.out.println(wait.until(ExpectedConditions.visibilityOf(noteField)).getText());
        return this;
    }

    public String getLink() {
        return link;
    }
}
