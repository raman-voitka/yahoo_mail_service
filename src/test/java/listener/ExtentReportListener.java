package listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

import static com.aventstack.extentreports.Status.*;
import static com.aventstack.extentreports.markuputils.ExtentColor.*;
import static com.aventstack.extentreports.markuputils.MarkupHelper.createLabel;
import static org.apache.commons.io.FileUtils.copyFile;
import static task_3_selenium.BaseTest.test;
import static task_3_selenium.utils.DriverInstanceUtils.getDriverInstance;

public class ExtentReportListener extends TestListenerAdapter {

    private String getTestName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    private void createAttachmentAsFile(String testName) {
        try {
            File screenshotFile = new File("target/" + testName + ".png");
            copyFile(((TakesScreenshot) getDriverInstance()).getScreenshotAs(OutputType.FILE), screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(PASS, createLabel(result.getName(), GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(FAIL, createLabel(result.getName(), RED));
        test.log(WARNING, result.getThrowable().fillInStackTrace());
        test.log(WARNING, result.getThrowable().getLocalizedMessage());
        createAttachmentAsFile(getTestName(result));
        test.addScreenCaptureFromPath(getTestName(result) + ".png");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(SKIP, createLabel(result.getName(), GREY));
        test.log(SKIP, result.getThrowable().fillInStackTrace());
        test.log(SKIP, result.getThrowable().getLocalizedMessage());
        createAttachmentAsFile(getTestName(result));
        test.addScreenCaptureFromPath(getTestName(result) + ".png");
    }
}