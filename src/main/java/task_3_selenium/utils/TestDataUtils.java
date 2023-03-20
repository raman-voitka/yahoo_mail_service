package task_3_selenium.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static task_3_selenium.utils.SimpleLoggerUtils.SIMPLE_LOG_TEST_DATA_UTILS;

public class TestDataUtils {

    private TestDataUtils() {
    }

    private static final String TEST_DATA_PATH = "src/main/resources/test_data.json";
    private static FileReader file = null;
    public static Map<String, Object> testData = getTestData();

    public static Map<String, Object> getTestData() {
        try {
            file = new FileReader(TEST_DATA_PATH);
            return new ObjectMapper().readValue(file, HashMap.class);
        } catch (IOException e) {
            SIMPLE_LOG_TEST_DATA_UTILS.error("file 'test_data.json' not found");
            return null;
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                SIMPLE_LOG_TEST_DATA_UTILS.error("TEST_DATA IO stream closing error");
            }
        }
    }
}