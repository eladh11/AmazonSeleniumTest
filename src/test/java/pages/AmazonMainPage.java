package pages;

import configuration.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonMainPage extends AbstractPage {

    private static final By TOP_SEARCH_BOX_ID = By.id("twotabsearchtextbox");
    private static final By SEARCH_BUTTON = By.id("nav-search-submit-button");
    private static final By GLOBAL_LOCATION = By.id("nav-global-location-popover-link");
    private static final By ZIP_CODE_INPUT = By.id("GLUXZipUpdateInput");
    private static final By ZIP_CODE_APPLY_BUTTON = By.xpath("//input[@aria-labelledby='GLUXZipUpdate-announce']");
    private static final By ZIP_CODE_CONFIRM_BUTTON = By.xpath("//*[@class='a-popover-footer']//*[@id='GLUXConfirmClose']");

    public AmazonMainPage(WebDriver driver) {
        super(driver);
    }

    public AmazonMainPage changeZipCodeToUSA(String zipCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(GLOBAL_LOCATION));
        driver.findElement(GLOBAL_LOCATION).click();
        driver.findElement(ZIP_CODE_INPUT).sendKeys(zipCode);
        driver.findElement(ZIP_CODE_APPLY_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ZIP_CODE_CONFIRM_BUTTON));
        driver.findElement(ZIP_CODE_CONFIRM_BUTTON).click();
        return this;
    }

    public AmazonMainPage typeToTopSearchBox(String searchItem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(Configuration.BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TOP_SEARCH_BOX_ID));
        driver.findElement(TOP_SEARCH_BOX_ID).sendKeys(searchItem);
        driver.findElement(SEARCH_BUTTON).click();
        return this;
    }
}