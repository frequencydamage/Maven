package andersenCourse.lesson12Refactor.task1Refactor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndersenLabPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AndersenLabPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@class, 'Button-module--button--094fe')][1]")
    private WebElement letsDiscussYourProjectButton;
    @FindBy(xpath = "//a[@href='/pricing']")
    private WebElement pricingButton;
    @FindBy(css = "div.Header-module--communicationWrapper--0f5ce button.BookingButton-module--button--440fc")
    private WebElement bookACallButton;

    public void openPage() {
        driver.get("https://andersenlab.com");
    }

    public boolean isLetsDiscussYourProjectButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(letsDiscussYourProjectButton)).isDisplayed();
    }

    public boolean isPricingButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(pricingButton)).isDisplayed();
    }

    public boolean isBookACallButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(bookACallButton)).isDisplayed();
    }
}
