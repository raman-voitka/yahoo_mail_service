package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static task_3_selenium.utils.TestDataUtils.testData;

public class MailPage extends BasePage {
    private static final By CREATE_NEW_MAIL_BUTTON = By.xpath("//a[@data-test-id='compose-button']");
    private static final String DIRECTORY_BUTTON = "//div[@data-test-folder-container='%s']";

    @Step("Click 'create new email' button")
    public void clickCreateNewMailButton() {
        clickElement(CREATE_NEW_MAIL_BUTTON);
    }

    @Step("Get into draft directory")
    public void clickDraftDirectory() {
        clickElement(By.xpath(String.format(DIRECTORY_BUTTON, testData.get("draft.directory"))));
    }

    @Step("Get into sent directory")
    public void clickSentDirectory() {
        clickElement(By.xpath(String.format(DIRECTORY_BUTTON, testData.get("sent.directory"))));
    }

    @Step("Perform logout from mail page")
    public void movePointerAndLogOutFromMail() {
        movePointer(UNIQUE_PAGE_ELEMENT);
        clickElement(LOG_OUT_BUTTON);
    }
}