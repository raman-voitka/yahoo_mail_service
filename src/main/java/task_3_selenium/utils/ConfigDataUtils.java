package task_3_selenium.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static task_3_selenium.utils.SimpleLoggerUtils.SIMPLE_LOG_CONFIG_DATA_UTILS;

public class ConfigDataUtils {

    private ConfigDataUtils() {
    }

    private static final String CONFIG_DATA_PATH = "src/main/resources/config_data.json";
    private static FileReader file = null;
    public static Map<String, Object> configData = getConfigData();

    public static Map<String, Object> getConfigData() {
        try {
            file = new FileReader(CONFIG_DATA_PATH);
            return new ObjectMapper().readValue(file, HashMap.class);
        } catch (IOException e) {
            SIMPLE_LOG_CONFIG_DATA_UTILS.error("file 'config_data.json' not found");
            return null;
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                SIMPLE_LOG_CONFIG_DATA_UTILS.error("CONFIG_DATA IO stream closing error");
            }
        }
    }

    public static int getDriverWaitTime() {
        return Integer.parseInt((String) configData.get("driver.wait.time"));
    }
}