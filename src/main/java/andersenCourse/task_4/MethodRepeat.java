package andersenCourse.task_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MethodRepeat {
    private static WebDriver driver = new ChromeDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    public static void videoRepeat() {
        driver.get("http://www.automationpractice.pl/index.php");
        search("Printed chiffon dress");
        addToCompareItem();
        redirect();
        search("Faded Short");
        addToCompareItem();
        sumTheComparison();
        driver.quit();
    }

    private static void search(String query) {
        WebElement searchField = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button"));
        searchField.sendKeys(query);
        searchButton.click();
        WebElement gridChangeButton = driver.findElement(By.cssSelector("i.icon-th-list"));
        gridChangeButton.click();
    }

    public static void addToCompareItem() {
        WebElement addToCompareButton = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div/div[3]/div/div[3]/div/a"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.add_to_compare")));
        addToCompareButton.click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("strong.total-compare-val"), "1"));
    }

    public static void redirect() {
        WebElement womenClothsLink = driver.findElement(By.cssSelector("a[title='Women']"));
        womenClothsLink.click();
    }

    public static void sumTheComparison() {
        WebElement compareButton = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div[2]/form/button/span"));
        compareButton.click();
    }
}
