package task_3_selenium.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static task_3_selenium.utils.DriverInstanceUtils.getDriverInstance;
import static task_3_selenium.utils.WaitUtils.*;

public abstract class BasePage {
    protected static final By LIST_OF_EMAILS = By.xpath("//a[@data-test-id='message-list-item']");
    protected static final By UNIQUE_PAGE_ELEMENT = By.id("ybarAccountMenuOpener");
    protected static final By NEXT_BUTTON = By.id("login-signin");
    protected static final By LOG_OUT_BUTTON = By.id("profile-signout-link");

    public void clickElement(By by) {
        waitForElementVisible(by).click();
    }

    public void fillTheField(By by, String string) {
        waitForElementVisible(by).clear();
        waitForElementVisible(by).sendKeys(string);
    }

    public void movePointer(By by) {
        Actions action = new Actions(getDriverInstance());
        action.moveToElement(waitForElementVisible(by)).build().perform();
    }

    public boolean isElementDisplayed(By by) {
        return waitForElementVisible(by).isDisplayed();
    }

    public boolean isListOfWebElementsEmpty(By by) {
        return !getDriverInstance().findElements(by).isEmpty();
    }

    public List<WebElement> getListOfWebElements(By by) {
        return waitForElementsVisible(by);
    }
}