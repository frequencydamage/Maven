package andersenCourse.lesson9TestCases;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AndersenWebPageTest {
    private WebDriver driver;
    private AndersenWebPage andersenWebPage;

    @BeforeMethod
    public void setUpDriver() {
        driver = new ChromeDriver();
        andersenWebPage = new AndersenWebPage(driver);
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }

    @AfterClass
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", System.getProperty("os.name"))
                        .put("OS.Version", System.getProperty("os.version"))
                        .put("Java.Version", System.getProperty("java.version"))
                        .put("Browser", "GoogleChrome")
                        .put("Browser.Version", ((ChromeDriver) driver).getCapabilities().getBrowserVersion())
                        .build());
    }

    @Test
    @Description("A test that verifies that the user cannot access the functionality without logging in")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature("Logging In")
    void isNotLoggingInWithNoData() {
        User user = UserBuilder.emptyUser().withEmail("").withPassword("").build();
        andersenWebPage
                .openLoginPage()
                .fillLoginData(user);

        boolean actual = andersenWebPage.waitElementIsDisabled(andersenWebPage.signInButton);
        Assert.assertTrue(actual);
    }

    @Test
    @Description("Testing for logging in with correct user data")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Logging In")
    void isLoggingInWithCorrectData() {
        User user = UserBuilder.defaultUser().build();
        andersenWebPage
                .openLoginPage()
                .fillLoginData(user)
                .signInButtonClick();

        boolean actual = andersenWebPage.waitVisibilityOfElement(andersenWebPage.editAccountButton);
        Assert.assertTrue(actual);
    }

    @Test
    @Description("A test that verifies that user cannot login into any account using already existing email and " +
            "incorrect password and appropriate message is shown")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Logging In")
    void isNotLoggingInWithIncorrectPassword() {
        User user = UserBuilder.defaultUser().withPassword("AffFfF33388JjJ").build();
        andersenWebPage
                .openLoginPage()
                .fillLoginData(user)
                .signInButtonClick();

        boolean actual = andersenWebPage.waitVisibilityOfElement(andersenWebPage.invalidPasswordMessage);
        Assert.assertTrue(actual);
    }

    @Test
    @Description("User should not be registered with invalid email-format")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Registration")
    void isNotRegisterWithInvalidEmail() throws InterruptedException {
        String invalidEmail = "&8888*#$AbCdE%!!@gmail.com";
        User user = UserBuilder.registerTestsUser().withEmail(invalidEmail).build();
        andersenWebPage
                .openRegistrationPage()
                .fillRegistrationData(user)
                .fillDateOfBirth(user)
                .submitButtonClick();

        boolean actual = andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountEmailValue);

        andersenWebPage.deleteAccount();

        Assert.assertFalse(actual);
    }

    @Description("Test that tries to register an account with unrealistic year of birth")
    @Severity(SeverityLevel.MINOR)
    @Feature("Registration")
    @Test
    void isNotRegisterWithInvalidYearOfBirth() throws InterruptedException {
        String invalidYearOfBirth = "2068";
        User user = UserBuilder.registerTestsUser().withYearOfBirth(invalidYearOfBirth).build();
        andersenWebPage
                .openRegistrationPage()
                .fillRegistrationData(user)
                .fillDateOfBirth(user)
                .submitButtonClick();

        boolean actual = andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountBirthDayValue);

        andersenWebPage.deleteAccount();

        Assert.assertFalse(actual);
    }

    @Description("Test that tries to register an account with already used email")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Registration")
    @Test
    void isNotRegisterWithUsedEmail() {
        User user = UserBuilder.defaultUser().build();
        andersenWebPage
                .openRegistrationPage()
                .fillRegistrationData(user)
                .fillDateOfBirth(user)
                .submitButtonClick();

        boolean actual = andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountEmailValue);
        Assert.assertFalse(actual);
    }

    @Description("A test that verifies the possibility to change user date of birth")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Account edition")
    @Test
    void isDateOfBirthChangeable() {
        Random random = new Random();
        String newYearOfBirth = String.valueOf(random.nextInt(180) + 1900);
        String newMonthOfBirth = "November";
        User user = UserBuilder.defaultUser().withMonthOfBirth(newMonthOfBirth).withYearOfBirth(newYearOfBirth).build();
        andersenWebPage
                .openLoginPage()
                .fillLoginData(user)
                .signInButtonClick();

        andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountBirthDayValue);
        String actualDateOfBirth = andersenWebPage.accountBirthDayValue.getText();

        andersenWebPage.editAccountButton.click();
        andersenWebPage
                .fillDateOfBirth(user)
                .submitButtonClick();

        andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountBirthDayValue);

        Assert.assertNotEquals(andersenWebPage.accountBirthDayValue.getText(), actualDateOfBirth);
    }

    @Description("A test that verifies the possibility to change user Name")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Account edition")
    @Test
    void isNameChangeable() {
        User user = UserBuilder.defaultUser().build();
        andersenWebPage
                .openLoginPage()
                .fillLoginData(user)
                .signInButtonClick();

        andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountFirstNameValue);
        String actualFirstName = andersenWebPage.accountFirstNameValue.getText();

        andersenWebPage.editAccountButtonClick();
        andersenWebPage.waitVisibilityOfElement(andersenWebPage.submitButton);
        andersenWebPage.firstNameField.clear();
        andersenWebPage.firstNameField.sendKeys("NameName");
        andersenWebPage.signInButtonClick();

        andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountFirstNameValue);

        boolean actual = !actualFirstName.equals(andersenWebPage.accountFirstNameValue.getText());
        Assert.assertTrue(actual);
    }

    @Description("A test that verifies the possibility to change user Lastname")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Account edition")
    @Test
    void isLastNameChangeable() {
        User user = UserBuilder.defaultUser().build();
        andersenWebPage
                .openLoginPage()
                .fillLoginData(user)
                .submitButtonClick();

        andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountLastNameValue);
        String actualLastName = andersenWebPage.accountLastNameValue.getText();

        andersenWebPage.editAccountButtonClick();
        andersenWebPage.waitVisibilityOfElement(andersenWebPage.submitButton);
        andersenWebPage.lastNameField.clear();
        andersenWebPage.lastNameField.sendKeys("SurnameName");
        andersenWebPage.submitButtonClick();

        andersenWebPage.waitVisibilityOfElement(andersenWebPage.accountFirstNameValue);

        boolean actual = !actualLastName.equals(andersenWebPage.accountFirstNameValue.getText());
        Assert.assertTrue(actual);
    }
}

