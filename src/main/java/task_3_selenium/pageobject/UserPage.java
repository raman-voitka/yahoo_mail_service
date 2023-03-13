package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class UserPage extends BasePage {
    private static final By MAIL_BUTTON = By.id("ybarMailLink");

    @Step("Is user page open?")
    public boolean isUserPageOpen() {
        return isElementDisplayed(UNIQUE_PAGE_ELEMENT);
    }

    @Step("Switch to mail page")
    public void clickMailButton() {
        clickElement(MAIL_BUTTON);
    }

    @Step("Perform logout from user page")
    public void movePointerAndLogOut() {
        movePointer(UNIQUE_PAGE_ELEMENT);
        clickElement(LOG_OUT_BUTTON);
    }
}