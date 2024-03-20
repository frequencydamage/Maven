package andersenCourse.lesson12Refactor.task3Refactor;

import org.openqa.selenium.By;
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

public class AndersenRegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;

    public AndersenRegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.react-datepicker__day.react-datepicker__day--007")
    private WebElement datePicker;
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//a[@href='/editAccount']")
    private WebElement editAccountButton;
    @FindBy(name = "firstName")
    private WebElement firstNameField;
    @FindBy(name = "lastName")
    private WebElement lastNameField;
    @FindBy(name = "dateOfBirth")
    private WebElement dateOfBirthField;
    @FindBy(name = "email")
    private WebElement emailField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(name = "passwordConfirmation")
    private WebElement passwordConfirmationField;

    public AndersenRegistrationPage openRegistrationPage() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
        return this;
    }

    public AndersenRegistrationPage fillPersonalData() {
        firstNameField.sendKeys("Valentin");
        lastNameField.sendKeys("Valentinov");
        dateOfBirthField.click();
        fillDateOfBirth();
        action.sendKeys(Keys.ENTER).perform();
        emailField.sendKeys("whateverwhatever@gmail.com");
        passwordField.sendKeys("98989898WwWw");
        passwordConfirmationField.sendKeys("98989898WwWw");
        return this;
    }

    private void fillDateOfBirth() {
        Select selectYear = new Select(driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//select[1]")));
        Select selectMonth = new Select(driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//select[2]")));
        selectYear.selectByValue("1993");
        selectMonth.selectByValue("July");
        datePicker.click();
    }

    public AndersenRegistrationPage confirmRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
        return this;
    }

    public boolean isRegistered() {
        return wait.until(ExpectedConditions.visibilityOf(editAccountButton)).isDisplayed();
    }
}
