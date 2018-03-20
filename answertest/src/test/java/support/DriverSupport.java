package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSupport {
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static String webDriverPath = "src/test/resources/webdrivers/chromedriver.exe";
    private static WebDriver driver;

    private DriverSupport() {
    }

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
//            driver.close();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty(WEBDRIVER_CHROME_DRIVER, webDriverPath);
            driver = new ChromeDriver();
        }

        return driver;
    }
}