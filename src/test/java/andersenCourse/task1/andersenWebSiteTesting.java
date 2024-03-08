package andersenCourse.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class andersenWebSiteTesting {
    static private WebDriver driver;
    static private WebDriverWait wait;

    @BeforeMethod
    private void setDriverNWait() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @AfterMethod
    private void closeDriver() {
        driver.quit();
    }

    @Test
    void letsDiscussYourProjectButtonVisibility() {
        driver.get("https://andersenlab.com");
        WebElement discussYourProjectButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"intro\"]/div/div[2]/div/div/div/button")));
        Assert.assertTrue(discussYourProjectButton.isDisplayed());
    }

    @Test
    void pricingButtonVisibility() {
        driver.get("https://andersenlab.com");
        WebElement pricingButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/pricing']")));
        Assert.assertTrue(pricingButton.isDisplayed());
    }

    @Test
    void bookACallButtonVisibility() {
        driver.get("https://andersenlab.com");
        WebElement bookACallButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/header/div/div[1]/div[2]/button/span")));
        Assert.assertTrue(bookACallButton.isDisplayed());
    }
}
