package com.znsio.e2e;

import com.znsio.e2e.entities.*;
import com.znsio.e2e.runner.*;
import org.joda.time.*;
import org.junit.jupiter.api.*;

import static com.znsio.e2e.runner.Setup.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

class RunnerTest {
    private final String stepDefDir = "com/znsio/e2e/steps";
    private final String logDir;

    {
        DateTime now = DateTime.now();
        logDir = "./target/" + now.getDayOfMonth() + "-" + now.getMonthOfYear() + "-" + now.getYear() + "_" + now.getHourOfDay() + "-" + now.getMinuteOfHour();
        System.setProperty("LOG_DIR", logDir);
        System.setProperty("OUTPUT_DIRECTORY", logDir);
    }

    @Test
    void localDefault() {
        String featuresDir = "./src/test/resources";
        System.setProperty("RUN_IN_CI", "false");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/config.properties", stepDefDir, featuresDir);
        String baseUrl = Runner.getFromEnvironmentConfiguration("BASE_URL");
        assertThat(baseUrl)
                .as("environment config is incorrect")
                .isEqualTo("http://the-internet.herokuapp.com/");

        String actualTestData = Runner.getTestData("GMAIL_USER_1_EMAIL");
        assertThat(actualTestData)
                .as("environment config is incorrect")
                .isEqualTo("mytestemail@gmail.com");
    }

    @Test
    void localWindows() {
        String featuresDir = "./src/test/resources";
        System.setProperty("RUN_IN_CI", "false");
        System.setProperty("PLATFORM", "windows");
        System.setProperty("TAG", "@notepad");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/windows_config.properties", stepDefDir, featuresDir);
    }

    @Test
    void localAndroidCalculator() {
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("PLATFORM", Platform.android.name());
        System.setProperty("APP_PACKAGE_NAME", "com.android2.calculator3");
        System.setProperty("APP_PATH", "./src/test/resources/sampleApps/AndroidCalculator.apk");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/config.properties", stepDefDir, featuresDir);
//        runner.printProcessedConfiguration();
    }

    @Test
    void localAndroid() {
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("PLATFORM", Platform.android.name());
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/config.properties", stepDefDir, featuresDir);
//        runner.printProcessedConfiguration();
    }

    @Test
    void localWeb() {
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("PLATFORM", Platform.web.name());
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/config.properties", stepDefDir, featuresDir);
//        runner.printProcessedConfiguration();
    }

    @Test
    void localMultiUserAndroidWebTest() {
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("TAG", "@multiuser-android-web");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/config.properties", stepDefDir, featuresDir);
    }

    @Test
    void multiUserAndroidTest() {
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("TAG", "@multiuser-android and @login");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/config.properties", stepDefDir, featuresDir);
    }

    @Test
    void headspinWeb() {
        String HEADSPIN_KEY = System.getenv("HEADSPIN_KEY");
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("PLATFORM", Platform.web.name());
        System.setProperty("RUN_IN_CI", "true");
        System.setProperty("TAG", "@login");
        System.setProperty(CLOUD_KEY, HEADSPIN_KEY);
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/headspin_config.properties", stepDefDir, featuresDir);
    }

    @Test
    void headspinMultiUserAndroidWebTest() {
        String HEADSPIN_KEY = System.getenv("HEADSPIN_KEY");
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty(CLOUD_KEY, HEADSPIN_KEY);
        System.setProperty("RUN_IN_CI", "true");
        System.setProperty("TAG", "@multiuser-android-web");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/headspin_config.properties", stepDefDir, featuresDir);
    }

    @Test
    void headspinAndroidTest() {
        String HEADSPIN_KEY = System.getenv("HEADSPIN_KEY");
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("RUN_IN_CI", "true");
        System.setProperty("TAG", "@login");
        System.setProperty(CLOUD_KEY, HEADSPIN_KEY);
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/headspin_config.properties", stepDefDir, featuresDir);
    }

    @Test
    void headspinMultiUserAndroidTest() {
        String HEADSPIN_KEY = System.getenv("HEADSPIN_KEY");
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("RUN_IN_CI", "true");
        System.setProperty("TAG", "@multiuser-android and @login");
        System.setProperty(CLOUD_KEY, HEADSPIN_KEY);
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/headspin_config.properties", stepDefDir, featuresDir);
    }

    @Test
    void pCloudyAndroidCloudTest() {
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("RUN_IN_CI", "true");
        System.setProperty("TAG", "@login");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/pcloudy_config.properties", stepDefDir, featuresDir);
    }

    @Test
    void pCloudyMultiUserAndroidCloudTest() {
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("RUN_IN_CI", "true");
        System.setProperty("TAG", "@multiuser-android and @login");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/pcloudy_config.properties", stepDefDir, featuresDir);
    }

    @Test
    void browserStackAndroidCloudTest() {
        String featuresDir = "./src/test/resources/com/znsio/e2e/features";
        System.setProperty("RUN_IN_CI", "true");
//        System.setProperty("TAG", "@login");
        Runner runner = new Runner("./src/test/resources/com/znsio/e2e/features/configs/browserStack_config.properties", stepDefDir, featuresDir);
    }

}