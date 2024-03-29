package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static task_3_selenium.utils.WaitUtils.waitForElementBecomeInvisible;

public class AcceptCookiePage extends BasePage {
    private static final By ACCEPT_ALL_BUTTON = By.xpath("//button[@name='agree']");

    @Step("Is accept cookie page open?")
    public void isAcceptCookiePageOpen() {
        if (isListOfWebElementsNotEmpty(ACCEPT_ALL_BUTTON)) {
            clickElement(ACCEPT_ALL_BUTTON);
            waitForElementBecomeInvisible(ACCEPT_ALL_BUTTON);
        }
    }
}