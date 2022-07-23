package amazon_tests;

import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class AbstractTest {

    protected WebDriver driver;

    public void setupAmazonWebsite() {
        System.setProperty(Configuration.CHROME_BROWSER, Configuration.CHROME_DRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(Configuration.BASE_URL);
        driver.manage().window().maximize();
        driver.get(Configuration.BASE_URL);
    }
}