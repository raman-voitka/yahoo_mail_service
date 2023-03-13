package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import task_3_selenium.model.EmailModel;

import java.util.ArrayList;
import java.util.List;

public class DraftPage extends BasePage {
    private static final By SEND_BUTTON = By.xpath("//button[@data-test-id='compose-send-button']");
    private static final By LAST_DRAFT_FROM_DRAFTS_LIST = By.xpath("//ul[@role='list']//li[2]//a");

    @Step("Click last draft")
    public void clickLastDraft() {
        clickElement(LAST_DRAFT_FROM_DRAFTS_LIST);
    }

    @Step("Click 'send' button")
    public void clickSendButton() {
        clickElement(SEND_BUTTON);
    }

    @Step("Get list of draft emails")
    public List<EmailModel> getListOfEmails() {
        List<EmailModel> listOfDrafts = new ArrayList<>();
        for (int i = 0; i < getListOfWebElements(LIST_OF_EMAILS).size(); i++) {
            EmailModel emailModel = new EmailModel();
            String[] draftAsArray = getListOfWebElements(LIST_OF_EMAILS).get(i).getText().split("\n");
            emailModel.setAddressee(draftAsArray[0]);
            emailModel.setSubject(draftAsArray[1]);
            emailModel.setBody(draftAsArray[3]);
            listOfDrafts.add(emailModel);
        }
        return listOfDrafts;
    }
}