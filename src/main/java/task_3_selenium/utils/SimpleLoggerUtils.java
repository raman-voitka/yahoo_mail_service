package task_3_selenium.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_3_selenium.webdriverinstance.WebDriverInstance;

public class SimpleLoggerUtils {

    private SimpleLoggerUtils() {
    }

    public static final Logger SIMPLE_LOG_DRIVER_INSTANCE_UTILS = LoggerFactory.getLogger(SimpleLoggerUtils.class);
    public static final Logger SIMPLE_LOG_CONFIG_DATA_UTILS = LoggerFactory.getLogger(ConfigDataUtils.class);
    public static final Logger SIMPLE_LOG_TEST_DATA_UTILS = LoggerFactory.getLogger(TestDataUtils.class);
    public static final Logger SIMPLE_LOG_WEB_DRIVER_DISCOVERY = LoggerFactory.getLogger(WebDriverInstance.class);
}