package andersenCourse.webSiteLoginTestWParameters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    @Test
    @Parameters({"email", "password"})
    void userLoginTest(String email, String password) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://qa-course-01.andersenlab.com/login");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        By logoutButtonPath = By.xpath("//*[@id=\"root\"]/div/div/div/p");
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButtonPath));
        Assert.assertTrue(logoutButton.isDisplayed());
        logoutButton.click();
        driver.quit();
    }
}
