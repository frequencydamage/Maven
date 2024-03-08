package andersenCourse.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestScenarioSecond {
    static private WebDriver driver;
    static private WebDriverWait wait;
    static private Actions action;

    @BeforeClass
    private void setDriverNWait() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        action = new Actions(driver);
    }

    @Test
    void scenarioRepeat() {
        //Test is runnable and user does not exist
        driver.get("https://qa-course-01.andersenlab.com/registration");
        action
                .sendKeys(Keys.TAB).sendKeys("Name")
                .sendKeys(Keys.TAB).sendKeys("Surname")
                .sendKeys(Keys.TAB).build().perform();
        Select selectYear = new Select(driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//select[1]")));
        Select selectMonth = new Select(driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//select[2]")));
        selectYear.selectByValue("1993");
        selectMonth.selectByValue("July");
        driver.findElement(By.cssSelector("div.react-datepicker__day.react-datepicker__day--007")).click();
        action
                .sendKeys(Keys.ENTER)
                .sendKeys(Keys.TAB).sendKeys("heyheyhey@gmail.com")
                .sendKeys(Keys.TAB).sendKeys("9999444HHhN")
                .sendKeys(Keys.TAB).sendKeys("9999444HHhN")
                .build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']:not([disabled])")));
        action.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).isDisplayed());
    }
}
