package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

import static task_3_selenium.utils.WaitUtils.waitForElementBecomeInvisible;

public class AcceptCookiePage extends BasePage {
    private static final By ACCEPT_ALL_BUTTON = By.xpath("//button[@class='btn primary']");

    @Step("Is accept cookie page open?")
    public void isAcceptCookiePageOpen() {
        if (isListOfWebElementsEmpty(ACCEPT_ALL_BUTTON)) {
            clickElement(ACCEPT_ALL_BUTTON);
            waitForElementBecomeInvisible(ACCEPT_ALL_BUTTON);
        }
    }
}