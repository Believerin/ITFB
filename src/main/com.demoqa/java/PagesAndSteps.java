import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PagesAndSteps {
    public WebDriver webDriver;

    public PagesAndSteps(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div[1]/div/div[2]")
    private WebElement clickingElements;

    @FindBy(id = "item-0")
    private WebElement clickingTextBox;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[1]/div[2]/input")
    private WebElement fillFormName;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[2]/div[2]/input")
    private WebElement fillFormEmail;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[3]/div[2]/textarea")
    private WebElement fillFormCurrentAddress;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[4]/div[2]/textarea")
    private WebElement fillFormPermanentAddress;

    @FindBy(css = "#submit")
    private WebElement submission;

    @FindBy(id = "name")
    private WebElement checkingName;

    @FindBy(id = "email")
    private WebElement checkingEmail;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]/div/p[3]")
    private WebElement checkingCurrentAddress;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]/div/p[4]")
    private WebElement checkingPermanentAddress;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[5]")
    private WebElement clickingButtons;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/button")
    private WebElement clickingMe;

    @FindBy(id = "rightClickBtn")
    private WebElement rightClickingMe;

    @FindBy(id = "doubleClickBtn")
    private WebElement doubleClickingMe;

    @FindBy(id = "dynamicClickMessage")
    private WebElement appearingMessageAfterClickMe;

    @FindBy(id = "rightClickMessage")
    private WebElement appearingMessageAfterRightClickMe;

    @FindBy(id = "doubleClickMessage")
    private WebElement appearingMessageAfterDoubleClickMe;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/div[3]/span/div")
    private WebElement alertsFrameWindows;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div/div[3]/div/ul/li[1]")
    private WebElement browserWindows;

    @FindBy(id = "tabButton")
    private WebElement newTab;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/button")
    private WebElement newWindow;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div/div[3]/div/ul/li[2]")
    private WebElement alerts;

    @FindBy(css = "#alertButton")
    private WebElement clickingButton;

    @FindBy(css = "#timerAlertButton")
    private WebElement clickingButtonWithTimer;

    @FindBy(css = "#confirmButton")
    private WebElement clickingButtonWithConfirm;

    @FindBy(css = "#confirmResult")
    private WebElement checkingOkAfterConfirm;

    @FindBy(css = "#promtButton")
    private WebElement promptButton;

    @FindBy(css = "#promptResult")
    private WebElement checkingPromptResult;

    @Step("Переход на сайт")
    public void visitSite() {
        webDriver.get("https://demoqa.com/");
    }

    @Step("Нажатие на \"Elements\"")
    public void clickElements() {
        clickingElements.click();
    }

    @Step("Нажатие на \"Text box\"")
    public void clickTextBox() {
        clickingTextBox.click();
    }

    @Step("Заполнение полей: Full Name, Email, Current Address, Permanent Address")
    public void sendKeysOfUser(String attribute) {
        switch (attribute) {
            case "name":
                fillFormName.sendKeys("Full Name");
                break;
            case "email":
                fillFormEmail.sendKeys("Email@email.ru");
                break;
            case "currentAddress":
                fillFormCurrentAddress.sendKeys("Current Address");
                break;
            case "permanentAddress":
                fillFormPermanentAddress.sendKeys("Permanent Address");
                break;
            default:
                System.out.println("Неверный атрибут");
        }
    }

    @Step("Нажатие на кнопку \"Submit\"")
    public void clickSubmit() {
        submission.click();
    }

    @Step("Проверика, что данные в блоке сохранены корректно")
    public String getAttributeUser(String attribute) {
        String property = "innerText";
        switch (attribute) {
            case "name":
                return checkingName.getAttribute(property);
            case "email":
                return checkingEmail.getAttribute(property);
            case "currentAddress":
                return checkingCurrentAddress.getAttribute(property);
            case "permanentAddress":
                return checkingPermanentAddress.getAttribute(property);
            default:
                return "Oh";
        }
    }

    @Step("Нажатие на кнопку \"Buttons\"")
    public void clickButtons() {
        clickingButtons.click();
    }

    @Step("Нажатие на кнопку \"Click me\"")
    public void clickMe() {
        clickingMe.click();
    }

    @Step("Проверитка, что появился текст \"You have done a {attribute} click\"")
    public String getAttributeOfClicks(String attribute) {
        String property = "innerText";
        switch (attribute) {
            case "dynamic":
                return appearingMessageAfterClickMe.getAttribute(property);
            case "right":
                return appearingMessageAfterRightClickMe.getAttribute(property);
            case "double":
                return appearingMessageAfterDoubleClickMe.getAttribute(property);
            default:
                return "Oh";
        }
    }

    @Step("Нажатие на кнопку \"Right Click me\"")
    public void rightClickMe() {
        Actions actions = new Actions(webDriver);
        actions.scrollByAmount(0, 100).contextClick(rightClickingMe).build().perform();
    }

    @Step("Нажатие на кнопку \"Double Click me\"")
    public void doubleClickMe() {
        Actions actions = new Actions(webDriver);
        actions.doubleClick(doubleClickingMe).build().perform();
    }

    @Step("Нажатие на кнопку \"Alerts, Frame & Windows\"")
    public void clickAlertsFrameWindows() {
        alertsFrameWindows.click();
    }

    @Step("Нажатие на кнопку \"Browser Windows\"")
    public void clickBrowserWindows() {
/*        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(browserWindows));*/
        browserWindows.click();
    }

    @Step("Нажатие на кнопку \"New Tab\"")
    public void clickNewTab() {
        newTab.click();
    }

    @Step("Закрытие новой вкладки")
    public void closeNewTab() {
        webDriver.close();
    }

    @Step("Нажатие на кнопку \"New window\"")
    public void clickNewWindow() {
        newWindow.click();
    }

    @Step("Закрытие нового окна")
    public void closeNewWindow() {
        webDriver.close();
    }

    @Step("Нажатие на кнопку \"Alerts\"")
    public void clickAlerts() {
        alerts.click();
    }

    @Step("Нажатие на кнопку \"Click me\" рядом с Click Button to see alert")
    public void clickButton() {
        clickingButton.click();
    }

    public void closeAlert(Alert alert) {
        alert.accept();
    }

    @Step("Нажатие на кнопку \"Click me\" рядом с On button click, alert will appear after 5 seconds")
    public void clickButtonWithTimer() {
        clickingButtonWithTimer.click();
    }

    @Step("Нажатие кнопки \"Да\" в уведомлении")
    public void closeAlertWithTimer(Alert alert) {
        alert.accept();
    }

    @Step("Нажатие на кнопку \"Click me\" рядом с On button click, confirm box will appear")
    public void clickButtonWithConfirm() {
        clickingButtonWithConfirm.click();
    }

    @Step("Нажатие кнопки \"Да\" в уведомлении")
    public void confirmBox(Alert alert) {
        alert.accept();
    }

    @Step("Проверка, что появился текст \"You selected Ok\"")
    public String checkOkAfterConfirm() {
        return checkingOkAfterConfirm.getAttribute("innerText");
    }

    @Step("Нажатие на кнопку \"Click me\" рядом с On button click, prompt box will appear")
    public void clickPromptButton() {
        promptButton.click();
    }

    @Step("Заполнение поля данными: Test name")
    public void sendKeysOfName(Alert alert) {
        alert.sendKeys("Test name");
        alert.accept();
    }

    @Step("Проверка, что появился текст \"You entered Test name\"")
    public String getAttributeOfClickPrompt() {
        return checkingPromptResult.getAttribute("innerText");
    }

}
