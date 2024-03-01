package andersenCourse.task_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class WindowManager {
    private static WebDriver driver;

    static void TabsOpener(List<String> tabList) {
        driver = new ChromeDriver();
        for (int i = 0; i < tabList.size(); i++) {
            if (i != tabList.size() - 1) {
                driver.get(tabList.get(i));
                driver.switchTo().newWindow(WindowType.TAB);
            } else {
                driver.get(tabList.get(i));
            }
        }
    }

    public static void WindowSwitchAndClose(String titleToClose) throws InterruptedException {
        Set<String> openedTabs = new LinkedHashSet<>(driver.getWindowHandles());
        for (String link : openedTabs) {
            driver.switchTo().window(link);
            System.out.println("Current WebPage title: " + driver.getTitle() + "\nCurrent WebPageLink: " + driver.getCurrentUrl());
            Thread.sleep(2000);
            if (driver.getTitle().toLowerCase().contains(titleToClose.toLowerCase())) {
                driver.close();
            }
        }
        driver.quit();
    }
}