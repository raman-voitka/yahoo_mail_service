package task_3_selenium;

import com.aventstack.extentreports.Status;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import listener.ExtentReportListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import task_3_selenium.listener.FailureListener;

import static org.testng.Assert.assertTrue;
import static task_3_selenium.utils.ConfigDataUtils.configData;
import static task_3_selenium.utils.DriverInstanceUtils.closeBrowser;
import static task_3_selenium.utils.DriverInstanceUtils.getDriverInstance;

@Epic("Yahoo.com. Login, sending mail, logout tests")
@Listeners({FailureListener.class, ExtentReportListener.class})
public class MailYahooTest extends BaseTest {
    @BeforeMethod
    private void openHomePage() {
        getDriverInstance().get((String) configData.get("homepage.url"));
        acceptCookiePage.isAcceptCookiePageOpen();
    }

    @Test
    @Description("Yahoo.com. Verify that the login and logout are successful")
    public void performLoginLogout() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(Status.INFO, "Test started");
        test.assignCategory("UI");
        performLogin();
        softTestng.assertTrue(userPage.isUserPageOpen(), "Login process is unsuccessful");
        userPage.movePointerAndLogOut();
        assertTrue(homePage.isHomePageOpen(), "Logout process is unsuccessful");
    }

    @Test
    @Description("Yahoo.com. Verify that created draft email is stored in the drafts directory")
    public void createMailAndSaveAsDraft() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(Status.INFO, "Test started");
        test.assignCategory("UI");
        performLogin();
        createAndSaveEmailAsDraft();
        soft.assertThat(draftPage.getListOfEmails())
                .as("There is no " + email + " in the draft directory")
                .contains(email);
        mailPage.movePointerAndLogOutFromMail();
    }

    @Test
    @Description("Yahoo.com. Verify that created email was sent")
    public void createMailAndSendIt() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(Status.INFO, "Test started");
        test.assignCategory("UI");
        performLogin();
        createAndSaveEmailAsDraft();
        draftPage.clickLastDraft();
        draftPage.clickSendButton();
        soft.assertThat(draftPage.getListOfEmails())
                .as("There is " + email + " in the draft directory")
                .doesNotContain(email);
        mailPage.clickSentDirectory();
        soft.assertThat(sentPage.getListOfEmails())
                .as("There is no " + email + " in the sent directory")
                .contains(email);
        mailPage.movePointerAndLogOutFromMail();
    }

    @Test
    @Description("Yahoo.com. Test which is failed")
    public void testFailure() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(Status.INFO, "Test started");
        test.assignCategory("UI");
        assertTrue(homePage.findNotExistWebElement(), "Can't find web element on the home page");
    }

    @AfterClass
    private void quitBrowser() {
        closeBrowser();
    }
}