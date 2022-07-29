package web;

import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverFactory {

    public static WebDriver getDriver(BrowserType browserType) {
        WebDriver driver = null;

        switch (browserType) {
            case CHROME:
                System.setProperty(Configuration.CHROME_BROWSER, Configuration.CHROME_DRIVER);
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                // TODO - add firefox
                // System.setProperty("", "");
                driver = new FirefoxDriver();
                break;
            case EDGE:
                // TODO - add edge
                // System.setProperty("", "");
                driver = new EdgeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(Configuration.BASE_URL);
        driver.manage().window().maximize();
        return driver;
    }
}
