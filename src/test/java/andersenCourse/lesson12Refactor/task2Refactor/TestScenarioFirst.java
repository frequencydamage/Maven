package andersenCourse.lesson12Refactor.task2Refactor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestScenarioFirst {
    private static WebDriver driver;
    private BrowserPerformer browserPerformer;
    private GuinnessPage guinnessPage;
    private AlertsDemoPage alertsDemoPage;
    private HTMLFormsPage htmlFormsPage;

    @BeforeClass
    public void setUpDriver() {
        driver = new ChromeDriver();
        browserPerformer = new BrowserPerformer(driver);
        guinnessPage = new GuinnessPage(driver);
        alertsDemoPage = new AlertsDemoPage(driver);
        htmlFormsPage = new HTMLFormsPage(driver);
    }

    @AfterClass
    public void driverQuit() {
        driver.quit();
    }

    @Test
    void scenarioRepeat() {
        browserPerformer.openGoogleSearch();
        browserPerformer
                .searchForQuery(guinnessPage.getLink())
                .openInNewTab();
        browserPerformer
                .searchForQuery(alertsDemoPage.getLink())
                .openInNewTab();
        browserPerformer
                .openInCurrentTab(htmlFormsPage.getLink());
        htmlFormsPage
                .fillData()
                .getNoteFieldText();
        browserPerformer.moveToNextTab();
        guinnessPage
                .fillPersonalData()
                .commitRegistration()
                .getValidationMessage();
        browserPerformer.moveToNextTab();
        alertsDemoPage.performButtonsAndDisplayOutput();
    }
}
