package task_3_selenium.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.testng.Reporter.log;
import static task_3_selenium.utils.ConfigDataUtils.configData;
import static task_3_selenium.utils.DriverInstanceUtils.getDriverInstance;

public class FailureListener implements ITestListener {
    private File screenshotFile;

    private String getTestName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    private String getLocalDateAndTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTimePattern = "yyyy-MM-dd HH-mm-ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return dateTimeFormatter.format(localDateTime);
    }

    private void createAttachmentAsFile(String testName) {
        try {
            screenshotFile = new File(configData.get("screenshot.path") + testName + "_" + getLocalDateAndTime() + ".png");
            copyFile(((TakesScreenshot) getDriverInstance()).getScreenshotAs(OutputType.FILE), screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        createAttachmentAsFile(getTestName(result));
        log("<a href='" + screenshotFile.getAbsolutePath() + "'> <img src='" + screenshotFile.getAbsolutePath() +
                "' height='100' width='100'/> </a>");
    }
}