import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class ITFBTest {

    public static WebDriver webDriver;
    public static PagesAndSteps pagesAndSteps;
    public static ArrayList<String> tabs;
    public static String originalWindow;
    private static String newTab;
    public static String newWindow;

    @BeforeAll
    public static void setUp() {
        webDriver = new ChromeDriver();
        pagesAndSteps = new PagesAndSteps(webDriver);
        tabs = new ArrayList<>();
    }

    @Test

    public void test() {
        pagesAndSteps.visitSite();
        originalWindow = webDriver.getWindowHandle();
        tabs.add(originalWindow);

        pagesAndSteps.clickElements();

        pagesAndSteps.clickTextBox();

        pagesAndSteps.sendKeysOfUser("name");
        pagesAndSteps.sendKeysOfUser("email");
        pagesAndSteps.sendKeysOfUser("currentAddress");
        pagesAndSteps.sendKeysOfUser("permanentAddress");

        pagesAndSteps.clickSubmit();

        Assertions.assertTrue("Full Name".equals(pagesAndSteps.getAttributeUser("name").substring(5))
                && "Email@email.ru".equals(pagesAndSteps.getAttributeUser("email").substring(6))
                &&"Current Address".equals(pagesAndSteps.getAttributeUser("currentAddress").substring(17))
                &&"Permanent Address".equals(pagesAndSteps.getAttributeUser("permanentAddress").substring(20)));

        pagesAndSteps.clickButtons();

        pagesAndSteps.clickMe();

        Assertions.assertEquals("You have done a dynamic click", pagesAndSteps.getAttributeOfClicks("dynamic"));

        pagesAndSteps.rightClickMe();

        Assertions.assertEquals("You have done a right click", pagesAndSteps.getAttributeOfClicks("right"));

        pagesAndSteps.doubleClickMe();

        Assertions.assertEquals("You have done a double click", pagesAndSteps.getAttributeOfClicks("double"));

        pagesAndSteps.clickAlertsFrameWindows();

        pagesAndSteps.clickBrowserWindows();

        pagesAndSteps.clickNewTab();

        newTab = webDriver.getWindowHandles().stream()
                .filter(tab -> !tab.equals(originalWindow))
                .findFirst().orElse(originalWindow);
        tabs.add(newTab);
        webDriver.switchTo().window(newTab);
        pagesAndSteps.closeNewTab();

        webDriver.switchTo().window(tabs.get(0));
        pagesAndSteps.clickNewWindow();

        newWindow = webDriver.getWindowHandles().stream()
                .filter(tab -> !tab.equals(originalWindow) && !tab.equals(newTab))
                .findFirst().orElse(originalWindow);
        webDriver.switchTo().window(newWindow);
        pagesAndSteps.closeNewWindow();

        webDriver.switchTo().window(tabs.get(0));
        pagesAndSteps.clickAlerts();

        pagesAndSteps.clickButton();

        Alert alert = webDriver.switchTo().alert();
        pagesAndSteps.closeAlert(alert);

        pagesAndSteps.clickButtonWithTimer();

        Alert fiveSecAlert = (new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent()));
        pagesAndSteps.closeAlertWithTimer(fiveSecAlert);

        pagesAndSteps.clickButtonWithConfirm();

        Alert okAlert = webDriver.switchTo().alert();
        pagesAndSteps.confirmBox(okAlert);

        Assertions.assertEquals("You selected Ok", pagesAndSteps.checkOkAfterConfirm());

        pagesAndSteps.clickPromptButton();

        Alert promptAlert = webDriver.switchTo().alert();
        pagesAndSteps.sendKeysOfName(promptAlert);

        Assertions.assertEquals("You entered Test name", pagesAndSteps.getAttributeOfClickPrompt());
    }
}