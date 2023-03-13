package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;
import static task_3_selenium.utils.TestDataUtils.testData;

public class LoginPage extends BasePage {
    private static final By EMAIL_FIELD = By.id("login-username");
    private static final String SAVED_EMAIL = "//a[@data-email='%s']";

    @Step("Fill in login field")
    public void fillEmailField() {
        if (isListOfWebElementsEmpty(EMAIL_FIELD)) {
            fillTheField(EMAIL_FIELD, (String) testData.get("yahoo.login"));
        } else {
            clickElement(xpath(String.format(SAVED_EMAIL, testData.get("yahoo.login"))));
        }
    }

    @Step("Click 'next' button on login page")
    public void clickNextButton() {
        clickElement(NEXT_BUTTON);
    }
}