package andersenCourse.lesson9TestCases;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndersenWebPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;

    public AndersenWebPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInButton;
    @FindBy(xpath = "//div[3]//p[contains(@class, 'text-base')]")
    private WebElement accountBirthDayValue;
    @FindBy(xpath = "//div[4]//p[contains(@class, 'text-base')]")
    private WebElement accountEmailValue;
    @FindBy(xpath = "//form/div/div[1]/div/span")
    private WebElement invalidEmailMessage;
    @FindBy(xpath = "//div[2]//div//span")
    private WebElement invalidPasswordMessage;
    @FindBy(css = "div.react-datepicker__day.react-datepicker__day--007")
    private WebElement datePicker;
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
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//a[@href='/editAccount']")
    private WebElement editAccountButton;
    @FindBy(xpath = "//div[1]/p[contains(@class, 'cursor-pointer')]")
    private WebElement logoutButton;
    @FindBy(xpath = "//button[@label='Yes']")
    private WebElement logoutConfirmationButton;

    public void openLoginPage() {
        driver.get("https://qa-course-01.andersenlab.com/login");
    }

    public void openRegistrationPage() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
    }


    public boolean loginWithoutData() {
        signInButton.click();
        return wait.until(ExpectedConditions.attributeContains(signInButton, "disabled", "true"));
    }

    public boolean loginWithIncorrectEmail() {
        emailField.sendKeys("namesurRrrname@gmail.com");
        passwordField.sendKeys("12345678");
        signInButton.click();
        return wait.until(ExpectedConditions.visibilityOf(invalidEmailMessage)).isDisplayed();
    }

    public boolean loginWithIncorrectPassword() {
        emailField.sendKeys("namesurname@gmail.com");
        passwordField.sendKeys("KkkFffU888222");
        signInButton.click();
        return wait.until(ExpectedConditions.visibilityOf(invalidPasswordMessage)).isDisplayed();
    }

    public boolean registerWithIncorrectBirthDate() {
        fillRegistrationData(true);
        confirmRegistration();
        return !wait.until(ExpectedConditions.visibilityOf(editAccountButton)).isDisplayed();

    }

    public boolean registerWithIncorrectEmail() {
        fillRegistrationData(false);
        confirmRegistration();
        return !wait.until(ExpectedConditions.visibilityOf(editAccountButton)).isDisplayed();
    }

    public boolean registerWithUsedEmail() {
        fillRegistrationData(true);
        try {
            confirmRegistration();
            wait.until(ExpectedConditions.visibilityOf(editAccountButton));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    public boolean passwordEditAndReLogin() {
        login();
        editPassword();
        wait.until(ExpectedConditions.visibilityOf(logoutButton)).click();
        wait.until(ExpectedConditions.visibilityOf(logoutConfirmationButton)).click();
        wait.until(ExpectedConditions.visibilityOf(emailField));
        login();
        return wait.until(ExpectedConditions.visibilityOf(invalidPasswordMessage)).isDisplayed();
    }

    public boolean dateOfBirthChange() {
        login();
        enterAccountEditor();
        String oldDateOfBirth = accountBirthDayValue.getText();
        fillDateOfBirth(2055, "August");
        action.sendKeys(Keys.ENTER).perform();
        wait.until(ExpectedConditions.visibilityOf(submitButton)).click();
        return !oldDateOfBirth.equals(accountBirthDayValue.getText());
    }

    public boolean emailChange() {
        login();
        String oldEmailValue = accountEmailValue.getText();
        enterAccountEditor();
        emailField.sendKeys("surnamename@gmail.com");
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        try {
            wait.until(ExpectedConditions.visibilityOf(accountEmailValue));
            if (!oldEmailValue.equals(accountEmailValue.getText())) {
                return true;
            } else {
                return false;
            }
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void fillRegistrationData(Boolean emailValidity) {
        firstNameField.sendKeys("Name");
        lastNameField.sendKeys("Surname");
        dateOfBirthField.click();
        fillDateOfBirth(1993, "July");
        action.sendKeys(Keys.ENTER).perform();
        if (!emailValidity) {
            emailField.sendKeys("&*//abcdeFg$$@gmail.com");
        } else {
            emailField.sendKeys("undefined@gmail.com");
        }
        passwordField.sendKeys("12345678");
        passwordConfirmationField.sendKeys("12345678");
    }

    private void fillDateOfBirth(int year, String month) {
        Select selectYear = new Select(driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//select[1]")));
        Select selectMonth = new Select(driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//select[2]")));
        selectYear.selectByValue(Integer.toString(year));
        selectMonth.selectByValue(month);
        datePicker.click();
    }

    private void confirmRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    private void login() {
        emailField.sendKeys("namesurname@gmail.com");
        passwordField.sendKeys("12345678");
        signInButton.click();
    }

    private void editPassword() {
        enterAccountEditor();
        passwordField.sendKeys("87654321");
        passwordConfirmationField.sendKeys("87654321");
        wait.until(ExpectedConditions.visibilityOf(submitButton)).click();
    }

    private void enterAccountEditor() {
        wait.until(ExpectedConditions.visibilityOf(editAccountButton)).click();
    }
}
