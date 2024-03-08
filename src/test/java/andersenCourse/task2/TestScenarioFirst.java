package andersenCourse.task2;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class TestScenarioFirst {
    static private WebDriver driver;
    static private WebDriverWait wait;
    static private Actions action;
    static private List<String> tabsID;

    @BeforeMethod
    private void setDriverNWait() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);
    }

    @AfterMethod
    private void driverQuit() {
        driver.quit();
    }

    @Test
    void scenarioRepeat() {
        driver.get("https://www.google.com/search");
        driver.findElement(By.id("L2AGLb")).click();
        searchForQuery("https://www.guinnessworldrecords.com/account/register");
        openInNewTab();
        searchForQuery("https://www.hyrtutorials.com/p/alertsdemo.html");
        openInNewTab();
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit");
        htmlFormsSitePerform();
        moveToNextTab();
        guinnessSitePerform();
        moveToNextTab();
        alertsDemoPerform();
    }

    private void searchForQuery(String searchQuery) {
        WebElement searchField = driver.findElement(By.name("q"));
        if (!searchField.getAttribute("value").isEmpty()) {
            searchField.clear();
        }
        searchField.sendKeys(searchQuery);
        action.sendKeys(Keys.ENTER).perform();
    }

    private void openInNewTab() {
        By elementToWaitFor = By.xpath("//div[@class='GKS7s'][1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementToWaitFor));
        action.moveToLocation(250, 250).click().sendKeys(Keys.TAB).keyDown(Keys.COMMAND).sendKeys(Keys.ENTER).keyUp(Keys.COMMAND).build().perform();
        tabsID = new ArrayList<>(driver.getWindowHandles());
        tabsID.remove(0);
    }

    private void htmlFormsSitePerform() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='accept-choices']"))).click();
        driver.switchTo().frame("iframeResult");
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='fname']"));
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lname']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        firstNameField.clear();
        firstNameField.sendKeys("Andrej");
        lastNameField.clear();
        lastNameField.sendKeys("Novak");
        submitButton.click();
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'w3-panel')]"))).getText());
    }

    private void guinnessSitePerform() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName")));
        driver.findElement(By.id("LastName")).sendKeys("Novak");
        driver.findElement(By.id("FirstName")).sendKeys("Andrej");
        driver.findElement(By.id("DateOfBirthDay")).sendKeys("09");
        driver.findElement(By.id("DateOfBirthMonth")).sendKeys("11");
        driver.findElement(By.id("DateOfBirthYear")).sendKeys("2000");
        Select countrySelect = new Select(driver.findElement(By.id("Country")));
        countrySelect.selectByValue("BY");
        driver.findElement(By.id("State")).sendKeys("Minsk");
        driver.findElement(By.id("EmailAddress")).sendKeys("andrej.novak.v@gmail.com");
        driver.findElement(By.id("ConfirmEmailAddress")).sendKeys("andrej.novak.v@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("wwwAaWw22234");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("555666FfRrJj");
        action.sendKeys(Keys.ENTER).perform();
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'validation-error')]//span"))).getText());
    }

    private void alertsDemoPerform() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='fc-button-label' and text()='Consent']"))).click();
        driver.findElement(By.id("alertBox")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output"))).getText());
        driver.findElement(By.id("confirmBox")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output"))).getText());
        driver.findElement(By.id("promptBox")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys("Final step of this task");
        driver.switchTo().alert().accept();
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output"))).getText());
    }

    private void moveToNextTab() {
        driver.switchTo().window(tabsID.get(0));
        tabsID.remove(0);
    }
}