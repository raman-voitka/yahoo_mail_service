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

    @Test(priority = 1)
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

    @Test(priority = 2)
    @Description("Yahoo.com. Verify that created draft email is stored in the drafts directory")
    public void createMailAndSaveAsDraft() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(INFO, "Test started");
        test.assignCategory("UI");
        test.info("<a href='https://www.google.com/'>link to test tracking system</a>");
        performLogin();
        createAndSaveEmailAsDraft();
        soft.assertThat(draftPage.getListOfEmails())
                .as("There is no " + email + " in the draft directory")
                .contains(email);
        mailPage.movePointerAndLogOutFromMail();
        soft.assertAll();
    }

    @Test(priority = 3)
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

    @Test(priority = 4)
    @Description("Yahoo.com. Test which is failed")
    public void demonstrateFailedTest() {
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
        test = extent.createTest(currentMethodName);
        test.log(INFO, "Test started");
        test.assignCategory("UI");
        test.info("<a href='https://www.google.com/'>link to test tracking system</a>");
        assertTrue(homePage.findNotExistWebElement(), "Can't find web element on the home page");
    }

    @Test(priority = 5)
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