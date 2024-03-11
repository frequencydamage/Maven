package andersenCourse.lesson12Refactor.task3Refactor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenarioSecond {
    //Test is runnable and user does not exist
    private AndersenRegistrationPage andersenRegistrationPage;
    private static WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        driver = new ChromeDriver();
        andersenRegistrationPage = new AndersenRegistrationPage(driver);
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }

    @Test
    void accountRegister() {
        andersenRegistrationPage
                .openRegistrationPage()
                .fillPersonalData()
                .confirmRegistration();
        Assert.assertTrue(andersenRegistrationPage.isRegistered());
    }
}
