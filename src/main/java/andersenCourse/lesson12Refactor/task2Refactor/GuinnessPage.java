package andersenCourse.lesson12Refactor.task2Refactor;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class GuinnessPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    private final String link = "https://www.guinnessworldrecords.com/account/register";


    public GuinnessPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "LastName")
    private WebElement lastNameField;

    @FindBy(id = "FirstName")
    private WebElement firstNameField;

    @FindBy(id = "DateOfBirthDay")
    private WebElement dateOfBDayField;

    @FindBy(id = "DateOfBirthMonth")
    private WebElement dateOfBirthMonthField;

    @FindBy(id = "DateOfBirthYear")
    private WebElement dateOfBirthYearField;
    @FindBy(id = "Country")
    private WebElement countrySelector;

    @FindBy(id = "State")
    private WebElement stateField;

    @FindBy(id = "EmailAddress")
    private WebElement emailField;

    @FindBy(id = "ConfirmEmailAddress")
    private WebElement emailConfirmationField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement passwordConfirmationField;

    @FindBy(xpath = "//span[contains(@class, 'validation-error')]//span")
    private WebElement validationMessage;


    public GuinnessPage fillPersonalData() {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.sendKeys("Popov");
        firstNameField.sendKeys("Dmitry");
        dateOfBDayField.sendKeys("22");
        dateOfBirthMonthField.sendKeys("05");
        dateOfBirthYearField.sendKeys("1995");
        Select countrySelect = new Select(countrySelector);
        countrySelect.selectByValue("BY");
        stateField.sendKeys("Minsk");
        emailField.sendKeys("dmitrovDmitry@gmail.com");
        emailConfirmationField.sendKeys("dmitrovDmitry@gmail.com");
        passwordField.sendKeys("wwwAaWw22234");
        passwordConfirmationField.sendKeys("555666FfRrJj");
        return this;
    }

    public GuinnessPage getValidationMessage() {
        System.out.println(wait.until(ExpectedConditions.visibilityOf(validationMessage)).getText());
        return this;
    }

    public GuinnessPage commitRegistration() {
        action.sendKeys(Keys.ENTER).perform();
        return this;
    }

    public String getLink() {
        return link;
    }
}
