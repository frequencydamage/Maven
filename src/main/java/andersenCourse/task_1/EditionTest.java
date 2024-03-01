package andersenCourse.task_1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class EditionTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void nameSurnameChange(String newName, String newSurname) {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        login("antonov@gmail.com", "antonanton");
        switchToProfileEdit();
        editAndSave(newName, newSurname);
        checkChanges();
    }

    public static void accountDeletion() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        login("antonov@gmail.com", "antonanton");
        switchToProfileEdit();
        deleteAccount();
    }

    private static void login(String email, String password) {
        driver.get("https://qa-course-01.andersenlab.com/login");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")).click();
        By editAccountButton = By.xpath("//*[@id=\"root\"]/div/div/div/a");
        wait.until(ExpectedConditions.visibilityOfElementLocated(editAccountButton));
    }

    private static void switchToProfileEdit() {
        By editAccountButton = By.xpath("//*[@id=\"root\"]/div/div/div/a");
        driver.findElement(editAccountButton).click();
    }

    private static void editAndSave(String newName, String newSurname) {
        WebElement nameField = driver.findElement(By.name("firstName"));
        nameField.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", nameField);
        nameField.sendKeys(newName);
        WebElement surnameField = driver.findElement(By.name("lastName"));
        surnameField.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", surnameField);
        surnameField.sendKeys(newSurname);
        By submitButton = By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/form/button");
        driver.findElement(submitButton).click();
    }

    private static void checkChanges() {
        WebElement firstNameField = driver.findElement(By.name("firstName"));
        String editedName = firstNameField.getAttribute("value");
        WebElement lastNameField = driver.findElement(By.name("lastName"));
        String editedSurname = lastNameField.getAttribute("value");
        if (!Objects.equals(editedName, "") && !Objects.equals(editedSurname, "")) {
            System.out.println("Test \"nameSurnameChange\" Passed");
        } else {
            System.out.println("Test \"nameSurnameChange\" Failed");
        }
    }

    private static void deleteAccount() {
        WebElement deleteAccountButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/form/div[2]/p"));
        deleteAccountButton.click();
        WebElement deleteConfirmationButton = driver.findElement(By.xpath("//*[@id=\"react-confirm-alert\"]/div/div/div/div/button[1]"));
        deleteConfirmationButton.click();
    }
}