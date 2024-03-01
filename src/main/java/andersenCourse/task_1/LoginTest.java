package andersenCourse.task_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void withValidData() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div/div[1]/label/input"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div/div[2]/label/input"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button"));
        emailInput.sendKeys("antonov@gmail.com");
        passwordInput.sendKeys("antonanton");
        submitButton.click();
        By editAccountButtonLocator = By.xpath("//*[@id=\"root\"]/div/div/div/a");
        try {
            WebElement editAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(editAccountButtonLocator));
            if (editAccountButton.isDisplayed()) {
                System.out.println("Test \"loginRedirection\" Passed");
            }
        } catch (Exception e) {
            System.out.println("Test \"loginRedirection\" Failed");
        } finally {
            driver.quit();
        }
    }

    public static void withInvalidData() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div/div[1]/label/input"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div/div[2]/label/input"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button"));
        emailInput.sendKeys("antonnnnnov@gmail.com");
        passwordInput.sendKeys("vasylvasyl");
        submitButton.click();
        By invalidDataMessageLocator = By.xpath("//*[@id=\"root\"]/div/div/div/form/div/div[1]/div/span");
        try {
            WebElement invalidDataMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(invalidDataMessageLocator));
            if (invalidDataMessage.isDisplayed()) {
                System.out.println("Test \"withInvalidData\" Passed");
            }
        } catch (Exception e) {
            System.out.println("Test \"withInvalidData\" Failed");
        } finally {
            driver.quit();
        }
    }
}
