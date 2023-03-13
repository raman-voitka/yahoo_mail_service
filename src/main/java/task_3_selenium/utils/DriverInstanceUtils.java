package task_3_selenium.utils;

import org.openqa.selenium.WebDriver;
import task_3_selenium.webdriverinstance.WebDriverInstance;

import static java.lang.Integer.parseInt;
import static java.time.Duration.ofSeconds;
import static task_3_selenium.utils.ConfigDataUtils.configData;
import static task_3_selenium.utils.LoggerUtils.LOG_DRIVER_INSTANCE_UTILS;

public class DriverInstanceUtils {

    private DriverInstanceUtils() {
    }

    private static WebDriver driver = WebDriverInstance.getWebDriver();

    public static WebDriver getDriverInstance() {
        driver.manage().window().maximize();
        driver.manage()
                .timeouts()
                .pageLoadTimeout(ofSeconds(parseInt((String) configData.get("page.load.timeout"))));
        return driver;
    }

    public static void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            LOG_DRIVER_INSTANCE_UTILS.error("Close browser error: {}", e.getMessage(), e);
        }
    }
}