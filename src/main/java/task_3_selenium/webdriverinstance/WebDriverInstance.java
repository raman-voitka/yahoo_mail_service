package task_3_selenium.webdriverinstance;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static task_3_selenium.utils.ConfigDataUtils.configData;
import static task_3_selenium.utils.SimpleLoggerUtils.SIMPLE_LOG_WEB_DRIVER_DISCOVERY;
import static task_3_selenium.webdriverinstance.BrowserFactory.getDriverCreator;

public class WebDriverInstance {
    private static ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal();

    private WebDriverInstance() {
    }

    private static void startBrowser() {
        try {
            remoteWebDriver.set(getDriverCreator((String) configData.get("driver.type")));
        } catch (NullPointerException | ExceptionInInitializerError e) {
            SIMPLE_LOG_WEB_DRIVER_DISCOVERY.error("Web driver initialization error");
        }
    }

    public static WebDriver getWebDriver() {
        if (remoteWebDriver.get() == null) {
            startBrowser();
        }
        return remoteWebDriver.get();
    }
}