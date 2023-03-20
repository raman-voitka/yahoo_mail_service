package task_3_selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import task_3_selenium.model.EmailModel;
import task_3_selenium.pageobject.*;

import static task_3_selenium.utils.RandomTestDataUtils.getRandomEmailSubjectOrSubject;
import static task_3_selenium.utils.TestDataUtils.testData;


public class BaseTest {
    public static ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/ExtentReport.html");
    public static ExtentReports extent = new ExtentReports();
    public static ExtentTest test;
    protected HomePage homePage = new HomePage();
    protected AcceptCookiePage acceptCookiePage = new AcceptCookiePage();
    protected LoginPage loginPage = new LoginPage();
    protected PasswordPage passwordPage = new PasswordPage();
    protected UserPage userPage = new UserPage();
    protected MailPage mailPage = new MailPage();
    protected CreateEmailPage createEmailPage = new CreateEmailPage();
    protected DraftPage draftPage = new DraftPage();
    protected SentPage sentPage = new SentPage();
    protected EmailModel email = new EmailModel();
    protected SoftAssert softTestng = new SoftAssert();
    protected SoftAssertions soft = new SoftAssertions();

    @BeforeClass
    public void setUp() {
        htmlReporter.config().setDocumentTitle("Example of Extent Report");
        htmlReporter.config().setReportName("Training Report");
        extent.attachReporter(htmlReporter);
    }

    protected void performLogin() {
        test.log(Status.INFO, "click 'sign in' button");
        homePage.clickSignInButton();
        test.log(Status.INFO, "fill in email field");
        loginPage.fillEmailField();
        test.log(Status.INFO, "click 'next' button");
        loginPage.clickNextButton();
        test.log(Status.INFO, "fill in password field");
        passwordPage.fillPasswordField();
        test.log(Status.INFO, "click 'next' button");
        passwordPage.clickNextButton();
    }

    protected void createAndSaveEmailAsDraft() {
        userPage.clickMailButton();
        mailPage.clickCreateNewMailButton();
        createEmailPage.fillAddresseeField();
        String subjectField = getRandomEmailSubjectOrSubject();
        createEmailPage.fillSubjectField(subjectField);
        String bodyField = getRandomEmailSubjectOrSubject();
        createEmailPage.fillBodyField(bodyField);
        email.setAddressee((String) testData.get("email.addressee"));
        email.setSubject(subjectField);
        email.setBody(bodyField);
        mailPage.clickDraftDirectory();
    }

    @AfterTest
    public void tearDown() {
        extent.flush();
    }
}