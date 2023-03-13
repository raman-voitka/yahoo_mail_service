package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import task_3_selenium.model.EmailModel;

import java.util.ArrayList;
import java.util.List;

public class SentPage extends BasePage {
    @Step("Get list of sent emails")
    public List<EmailModel> getListOfEmails() {
        List<EmailModel> listOfDrafts = new ArrayList<>();
        for (int i = 0; i < getListOfWebElements(LIST_OF_EMAILS).size(); i++) {
            EmailModel emailModel = new EmailModel();
            String[] draftAsArray = getListOfWebElements(LIST_OF_EMAILS).get(i).getText().split("\n");
            emailModel.setAddressee(draftAsArray[0]);
            emailModel.setSubject(draftAsArray[1]);
            emailModel.setBody(draftAsArray[2]);
            listOfDrafts.add(emailModel);
        }
        return listOfDrafts;
    }
}