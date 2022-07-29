package amazon_tests;

import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import web.BrowserType;
import web.WebDriverFactory;

public abstract class AbstractTest {

    protected WebDriver driver;

    public void setupAmazonWebsite() {
        driver = WebDriverFactory.getDriver(BrowserType.CHROME);
        driver.get(Configuration.BASE_URL);
    }
}