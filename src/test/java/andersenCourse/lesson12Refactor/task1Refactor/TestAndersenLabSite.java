package andersenCourse.lesson12Refactor.task1Refactor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAndersenLabSite {
    private static WebDriver driver;
    private AndersenLabPage andersenLabPage;

    @BeforeMethod
    public void setUpDriver() {
        driver = new ChromeDriver();
        andersenLabPage = new AndersenLabPage(driver);
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }

    @Test
    void letsDiscussYourProjectButtonVisibility() {
        andersenLabPage.openPage();
        Assert.assertTrue(andersenLabPage.isLetsDiscussYourProjectButtonVisible());
    }
    @Test
    void pricingButtonVisibility() {
        andersenLabPage.openPage();
        Assert.assertTrue(andersenLabPage.isPricingButtonVisible());
    }
    @Test
    void bookACallButtonVisibility() {
        andersenLabPage.openPage();
        Assert.assertTrue(andersenLabPage.isBookACallButtonVisible());
    }
}
