package andersenCourse.lesson9TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestAndersenWebPage {
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

    @Test
    void isNotLoggingInWithNoData() {
        andersenWebPage.openLoginPage();
        Assert.assertTrue(andersenWebPage.loginWithoutData());
    }

    @Test
    void isNotLoggingWithIncorrectEmail() {
        andersenWebPage.openLoginPage();
        Assert.assertTrue(andersenWebPage.loginWithIncorrectEmail());
    }

    @Test
    void isNotLoggingWithIncorrectPassword() {
        andersenWebPage.openLoginPage();
        Assert.assertTrue(andersenWebPage.loginWithIncorrectPassword());
    }

    @Test
    void isNotRegisteringWithInvalidEmail() {
        andersenWebPage.openRegistrationPage();
        Assert.assertTrue(andersenWebPage.registerWithIncorrectEmail());
    }

    @Test
    void isNotRegisteringWithIncorrectDate() {
        andersenWebPage.openRegistrationPage();
        Assert.assertTrue(andersenWebPage.registerWithIncorrectBirthDate());
    }

    @Test
    void isNotRegisteringWithUsedEmail() {
        andersenWebPage.openRegistrationPage();
        Assert.assertTrue(andersenWebPage.registerWithUsedEmail());
    }
    @Test
    void isDateOfBirthChangeable(){
        andersenWebPage.openLoginPage();
        Assert.assertFalse(andersenWebPage.dateOfBirthChange());
    }

    @Test
    void isEmailChangeable() {
        andersenWebPage.openLoginPage();
        Assert.assertTrue(andersenWebPage.emailChange());
    }
    @Test
    void isPasswordEditSucceeded() {
        andersenWebPage.openLoginPage();
        Assert.assertTrue(andersenWebPage.passwordEditAndReLogin());
    }
}
