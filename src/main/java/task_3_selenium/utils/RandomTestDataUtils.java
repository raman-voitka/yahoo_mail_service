package task_3_selenium.utils;

import java.util.Random;

import static task_3_selenium.utils.TestDataUtils.testData;

public class RandomTestDataUtils {

    private RandomTestDataUtils() {
    }

    private static Random random;

    public static String getRandomEmailSubjectOrSubject() {
        random = new Random();
        String[] subjectOrBody = ((String) testData.get("subject.body.list")).split(",");
        return subjectOrBody[random.nextInt(subjectOrBody.length)];
    }
}