package task_3_selenium;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import listener.ExtentReportListener;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import task_3_selenium.listener.FailureListener;
import task_3_selenium.model.EmailModel;

import java.util.List;

import static com.aventstack.extentreports.Status.INFO;
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
        test.log(INFO, "Test started");
        test.assignCategory("UI");
        test.info("<a href='https://www.google.com/'>link to test tracking system</a>");
        performLogin();
        softTestng.assertTrue(userPage.isUserPageOpen(), "Login process is unsuccessful");
        userPage.movePointerAndLogOut();
        softTestng.assertTrue(homePage.isHomePageOpen(), "Logout process is unsuccessful");
        softTestng.assertAll();
    }

    @Test
    @Description("Yahoo.com. Verify that created draft email is stored in the drafts directory")
    public void createMailAndSaveAsDraft() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(INFO, "Test started");
        test.assignCategory("UI");
        test.info("<a href='https://www.google.com/'>link to test tracking system</a>");
        performLogin();
        createAndSaveEmailAsDraft();
        test.log(INFO, "verify that created draft email is stored in the drafts directory");
        List<EmailModel> emailModelList = draftPage.getListOfEmails();
        soft.assertThat(emailModelList)
                .as("There is no " + email + " in the draft directory")
                .contains(email);
        soft.assertThat(emailModelList.size())
                .as("The number of draft emails is not equal 12")
                .isEqualTo(12);
        soft.assertThat(emailModelList.size())
                .as("The number of draft emails is not equal 15")
                .isEqualTo(15);
        test.log(INFO, "perform log out");
        mailPage.movePointerAndLogOutFromMail();
        soft.assertAll();
    }

    @Test
    @Description("Yahoo.com. Verify that created email was sent")
    public void createMailAndSendIt() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(INFO, "Test started");
        test.assignCategory("UI");
        test.info("<a href='https://www.google.com/'>link to test tracking system</a>");
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
        soft.assertAll();
    }

    @Test
    @Description("Yahoo.com. Test which is failed")
    public void demonstrateFailedTest() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(INFO, "Test started");
        test.assignCategory("UI");
        test.info("<a href='https://www.google.com/'>link to test tracking system</a>");
        assertTrue(homePage.findNotExistWebElement(), "Can't find web element on the home page");
    }

    @Test
    @Description("Yahoo.com. Test which is skipped")
    public void demonstrateSkippedTest() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(INFO, "Test started");
        test.assignCategory("UI");
        test.info("<a href='https://www.google.com/'>link to test tracking system</a>");
        throw new SkipException("Test is skipped");
    }

    @AfterClass
    private void quitBrowser() {
        closeBrowser();
    }
}