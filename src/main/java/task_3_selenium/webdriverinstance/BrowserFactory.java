package task_3_selenium.webdriverinstance;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BrowserFactory {
    public static RemoteWebDriver getDriverCreator(String driverType) throws NullPointerException {
        switch (driverType) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("lang=en-GB");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromedriver().setup();
                return new ChromeDriver(chromeOptions);
            }
            default -> throw new NullPointerException();
        }
    }
}