package browserStackTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.URL;

public class BaseTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public final String baseURL = "https://elpais.com/";

    public static final String USERNAME = "your_browserstack_username";
    public static final String ACCESS_KEY = "your_browserstack_access_key";
    public static final String BROWSERSTACK_URL =
        "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Parameters({"browserName", "os", "osVersion"})
    @BeforeTest
		public void setUp(@Optional("Chrome") String browserName,
                      @Optional("Windows") String os,
                      @Optional("10") String osVersion) throws Exception{
			WebDriverManager.chromedriver().setup();
			if (browserName.equalsIgnoreCase("Chrome")) {
            // Local ChromeDriver initialization
            driver.set(new ChromeDriver());
        } else {
            // For BrowserStack or RemoteWebDriver initialization
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", browserName);
            caps.setCapability("os", os);
            caps.setCapability("os_version", osVersion);
            caps.setCapability("browserVersion", "latest");
            caps.setCapability("name", "ElPais Opinion Test");

            // Initialize RemoteWebDriver (BrowserStack or other grid services)
            driver.set(new RemoteWebDriver(new URL(BROWSERSTACK_URL), caps));
        }
    
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }
}