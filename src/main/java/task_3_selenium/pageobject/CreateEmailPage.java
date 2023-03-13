package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static task_3_selenium.utils.TestDataUtils.testData;

public class CreateEmailPage extends BasePage {
    private static final By ADDRESSEE_FIELD = By.id("message-to-field");
    private static final By SUBJECT_FIELD = By.xpath("//input[@data-test-id='compose-subject']");
    private static final By BODY_FIELD = By.xpath("//div[@data-test-id='rte']");


    @Step("Fill in addressee field")
    public void fillAddresseeField() {
        fillTheField(ADDRESSEE_FIELD, (String) testData.get("email.addressee"));
    }

    @Step("Fill in subject field")
    public void fillSubjectField(String subjectField) {
        fillTheField(SUBJECT_FIELD, subjectField);
    }

    @Step("Fill in body field")
    public void fillBodyField(String bodyField) {
        fillTheField(BODY_FIELD, bodyField);
    }
}