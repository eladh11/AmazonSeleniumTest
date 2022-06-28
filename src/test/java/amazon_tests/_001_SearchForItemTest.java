package amazon_tests;

import com.amazon.automation.config.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class _001_SearchForItemTest {

    //String zipCode = "90011";
    public static final By TOP_SEARCH_BOX_ID = By.id("twotabsearchtextbox");
    public static final By SEARCH_BUTTON = By.id("nav-search-submit-button");
    public static final By ALL_RETURNED_ELEMENT = By.xpath("//div[@data-component-type='s-search-result']//h2");
    public static final By ADD_TO_CART = By.id("add-to-cart-button");
    public static final By SELECTED_PHONE_DESCRIPTION = By.xpath("//span[@class='a-truncate-cut']");
    public static final By LIST_OF_RESULTS_LINKS = By.xpath("//h2[contains(@class,'a-size-mini a-spacing-none a-color-base s-line-clamp-2')]/a");
    public static final By GLOBAL_LOCATION = By.id("nav-global-location-popover-link");
    public static final By ZIP_CODE_INPUT = By.id("GLUXZipUpdateInput");
    public static final By ZIP_CODE_DONE_BUTTON = By.id("a-autoid-3-announce");
    public static final By ZIP_CODE_APPLY_BUTTON = By.xpath("//input[@aria-labelledby='GLUXZipUpdate-announce']");

    @Test
    public void _001_SearchForItem() {
        try {
            System.setProperty(Configuration.CHROME_BROWSER, Configuration.CHROME_DRIVER);
            WebDriver driver = new ChromeDriver();
            // Implicitly Wait
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(Configuration.BASE_URL);
            driver.manage().window().maximize();

            // Explicit Wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            /*
            driver.findElement(GLOBAL_LOCATION).click();
            driver.findElement(ZIP_CODE_INPUT).sendKeys(zipCode);
            driver.findElement(ZIP_CODE_APPLY_BUTTON).click();
            driver.findElement(ZIP_CODE_DONE_BUTTON).click();
            */
            driver.findElement(TOP_SEARCH_BOX_ID).sendKeys(Configuration.SEARCH_ITEM);
            driver.findElement(SEARCH_BUTTON).click();

            List<WebElement> searchResults = driver.findElements(ALL_RETURNED_ELEMENT);
            System.out.println("Print the Results:");
            searchResults.forEach(result -> System.out.println(result.getText()));

//            int randomNumber = (int) ((Math.random() * (searchResults.size() - 0)));
            WebElement randomElement = searchResults.get(0);
            String randomElementDes = randomElement.getText();
            System.out.println("the selected phone is: " + randomElementDes);
            List<WebElement> linksFromResult = driver.findElements(LIST_OF_RESULTS_LINKS);
            linksFromResult.get(0).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART));
            driver.findElement(ADD_TO_CART).click();
//            String descriptionForSelectedPhone = driver.findElement(SELECTED_PHONE_DESCRIPTION).getText();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
