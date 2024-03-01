package andersenCourse.task_1;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationTest {
    static private WebDriver driver;
    static private WebDriverWait wait;

    public static void validEmailScenario() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://qa-course-01.andersenlab.com/registration");
        WebElement dummyButton = driver.findElement(By.name("firstName")); //Var added to deal with DateOfBirth PopUp
        dummyButton.sendKeys("Anton");
        driver.findElement(By.name("lastName")).sendKeys("Antonov");
        driver.findElement(By.name("email")).sendKeys("antonov@gmail.com");
        driver.findElement(By.name("dateOfBirth")).sendKeys("07/07/2000");
        driver.findElement(By.name("password")).sendKeys("antonanton");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("antonanton");
        dummyButton.click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")).click();
        By editAccountButtonLocator = By.xpath("//*[@id=\"root\"]/div/div/div/a");
        try {
            WebElement editAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(editAccountButtonLocator));
            if (editAccountButton.isDisplayed()) {
                System.out.println("Test \"validEmailScenario\" Passed");
            }
        } catch (TimeoutException e) {
            System.out.println("Test \"validEmailScenario\" Failed");
        } finally {
            driver.quit();
        }
    }

    public static void emptyFieldsScenario() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://qa-course-01.andersenlab.com/registration");
        By submitButtonLocator = By.xpath("//*[@id=\"root\"]/div/div/div/form/button");
        driver.findElement(submitButtonLocator).click();
        try {
            wait.until(ExpectedConditions.attributeToBe(submitButtonLocator, "disabled", "true"));
            System.out.println("Test \"emptyFieldsScenario\" Passed");
        } catch (TimeoutException e) {
            System.out.println("Test \"emptyFieldsScenario\" Failed");
        } finally {
            driver.quit();
        }
    }
}
