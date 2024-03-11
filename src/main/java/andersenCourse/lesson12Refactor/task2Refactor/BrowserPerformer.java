package andersenCourse.lesson12Refactor.task2Refactor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrowserPerformer {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    static private List<String> tabsID;

    public BrowserPerformer(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
    }

    public BrowserPerformer openGoogleSearch() {
        driver.get("https://www.google.com/search");
        driver.findElement(By.id("L2AGLb")).click();
        return this;
    }

    public BrowserPerformer searchForQuery(String searchQuery) {
        WebElement searchField = driver.findElement(By.name("q"));
        if (!searchField.getAttribute("value").isEmpty()) {
            searchField.clear();
        }
        searchField.sendKeys(searchQuery);
        action.sendKeys(Keys.ENTER).perform();
        return this;
    }

    public BrowserPerformer openInNewTab() {
        By searchIcon = By.xpath("//span[@class='z1asCe MZy1Rb']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchIcon));
        action.moveToLocation(250, 250).click().sendKeys(Keys.TAB).keyDown(Keys.COMMAND).sendKeys(Keys.ENTER).keyUp(Keys.COMMAND).build().perform();
        tabsID = new ArrayList<>(driver.getWindowHandles());
        tabsID.remove(0);
        return this;
    }
    public BrowserPerformer openInCurrentTab(String link) {
        driver.get(link);
        return this;
    }

    public BrowserPerformer moveToNextTab() {
        driver.switchTo().window(tabsID.get(0));
        tabsID.remove(0);
        return this;
    }
}
