package task_3_selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static task_3_selenium.utils.ConfigDataUtils.getDriverWaitTime;
import static task_3_selenium.utils.DriverInstanceUtils.getDriverInstance;

public class WaitUtils {

    private WaitUtils() {
    }

    public static WebElement waitForElementVisible(By by) {
        Wait<WebDriver> wait = new WebDriverWait(getDriverInstance(), ofSeconds(getDriverWaitTime()));
        wait.until(visibilityOfElementLocated(by));
        return getDriverInstance().findElement(by);
    }

    public static boolean waitForElementBecomeInvisible(By by) {
        Wait<WebDriver> wait = new WebDriverWait(getDriverInstance(), ofSeconds(getDriverWaitTime()));
        return wait.until(invisibilityOfElementLocated(by));
    }

    public static List<WebElement> waitForElementsVisible(By by) {
        Wait<WebDriver> wait = new WebDriverWait(getDriverInstance(), ofSeconds(getDriverWaitTime()));
        return wait.until(visibilityOfAllElementsLocatedBy(by));
    }
}