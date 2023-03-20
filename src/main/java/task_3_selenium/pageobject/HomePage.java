package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private static final By SIGN_IN_BUTTON = By.id("ybarAccountProfile");

    @Step("Click 'sign in' button on the home page")
    public void clickSignInButton() {
        clickElement(SIGN_IN_BUTTON);
    }

    @Step("Verify that the home page open")
    public boolean isHomePageOpen() {
        return isElementDisplayed(SIGN_IN_BUTTON);
    }

    @Step("Try to find web element which doesn't exist on the page")
    public boolean findNotExistWebElement() {
        return isElementDisplayed(NON_EXISTENT_PAGE_ELEMENT);
    }
}