package andersenCourse.lesson9TestCases;

import io.qameta.allure.Step;
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

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signInButton;
    @FindBy(xpath = "//div[1]/p[2]")
    WebElement accountFirstNameValue;
    @FindBy(xpath = "//div[2]/p[2]")
    WebElement accountLastNameValue;
    @FindBy(xpath = "//div[3]//p[contains(@class, 'text-base')]")
    WebElement accountBirthDayValue;
    @FindBy(xpath = "//div[4]//p[contains(@class, 'text-base')]")
    WebElement accountEmailValue;
    @FindBy(xpath = "//form/div/div[1]/div/span")
    WebElement invalidEmailMessage;
    @FindBy(xpath = "//div[2]//div//span")
    WebElement invalidPasswordMessage;
    @FindBy(css = "div.react-datepicker__day.react-datepicker__day--007")
    WebElement datePicker;
    @FindBy(name = "firstName")
    WebElement firstNameField;
    @FindBy(name = "lastName")
    WebElement lastNameField;
    @FindBy(name = "dateOfBirth")
    WebElement dateOfBirthField;
    @FindBy(name = "email")
    WebElement emailField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(name = "passwordConfirmation")
    WebElement passwordConfirmationField;
    @FindBy(css = "button[type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//a[@href='/editAccount']")
    WebElement editAccountButton;
    @FindBy(xpath = "//div//p[1]")
    WebElement deleteAccountButton;
    @FindBy(xpath = "//div[1]/p[contains(@class, 'cursor-pointer')]")
    WebElement logoutButton;
    @FindBy(xpath = "//button[@label='Yes']")
    WebElement accountActionsConfirmationButton;

    public AndersenWebPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Open page with login form")
    public AndersenWebPage openLoginPage() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        return this;
    }

    @Step("Open page with registration form")
    public AndersenWebPage openRegistrationPage() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
        return this;
    }

    public boolean waitElementIsDisabled(WebElement webElement) {
        return wait.until(ExpectedConditions.attributeContains(webElement, "disabled", "true"));
    }

    public boolean waitVisibilityOfElement(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Fill login data fields")
    public AndersenWebPage fillLoginData(User user) {
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        return this;
    }

    @Step("Fill all registration fields with data")
    public AndersenWebPage fillRegistrationData(User user) {
        waitVisibilityOfElement(firstNameField);
        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        passwordConfirmationField.sendKeys(user.getPassword());
        return this;
    }

    @Step("Fill date of birth")
    public AndersenWebPage fillDateOfBirth(User user) {
        dateOfBirthField.click();
        Select selectYear = new Select(driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//select[1]")));
        Select selectMonth = new Select(driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//select[2]")));
        selectYear.selectByValue(user.getYearOfBirth());
        selectMonth.selectByValue(user.getMonthOfBirth());
        datePicker.click();
        action.sendKeys(Keys.ENTER).perform();
        return this;
    }

    public AndersenWebPage deleteAccount() throws InterruptedException {
        //Method added to delete an account and tests to be re-runnable
        wait.until(ExpectedConditions.visibilityOf(editAccountButton)).click();
        wait.until(ExpectedConditions.visibilityOf(deleteAccountButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(accountActionsConfirmationButton)).click();
        Thread.sleep(1000);
        return this;
    }

    @Step("Click submit button")
    public AndersenWebPage submitButtonClick() {
        wait.until(ExpectedConditions.visibilityOf(submitButton)).click();
        return this;
    }

    @Step("Click Edit Account button")
    public AndersenWebPage editAccountButtonClick() {
        wait.until(ExpectedConditions.visibilityOf(editAccountButton)).click();
        return this;
    }

    @Step("Click Sign In button")
    public AndersenWebPage signInButtonClick() {
        wait.until(ExpectedConditions.visibilityOf(signInButton)).click();
        return this;
    }
}
