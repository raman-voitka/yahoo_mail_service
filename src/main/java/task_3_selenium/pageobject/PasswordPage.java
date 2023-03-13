package task_3_selenium.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static task_3_selenium.utils.TestDataUtils.testData;

public class PasswordPage extends BasePage {
    private static final By PASSWORD_FIELD = By.id("login-passwd");

    @Step("Fill in password field")
    public void fillPasswordField() {
        fillTheField(PASSWORD_FIELD, (String) testData.get("yahoo.password"));
    }

    @Step("Click 'next' button on password page")
    public void clickNextButton() {
        clickElement(NEXT_BUTTON);
    }
}