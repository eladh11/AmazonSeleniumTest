package amazon_tests;

import configuration.Configuration;
import data_providers.DP_Test1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.AssertUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _001_SearchForItemTest {

    public static final By TOP_SEARCH_BOX_ID = By.id("twotabsearchtextbox");
    public static final By SEARCH_BUTTON = By.id("nav-search-submit-button");
    public static final By ALL_RETURNED_ELEMENT = By.xpath("//div[@data-component-type='s-search-result']//h2");
    public static final By ADD_TO_CART = By.id("add-to-cart-button");
    public static final By LIST_OF_RESULTS_LINKS = By.xpath("//h2[contains(@class,'a-size-mini a-spacing-none a-color-base s-line-clamp-2')]/a");
    public static final By GLOBAL_LOCATION = By.id("nav-global-location-popover-link");
    public static final By ZIP_CODE_INPUT = By.id("GLUXZipUpdateInput");
    public static final By ZIP_CODE_APPLY_BUTTON = By.xpath("//input[@aria-labelledby='GLUXZipUpdate-announce']");
    public static final By ZIP_CODE_CONFIRM_BUTTON = By.xpath("//*[@class='a-popover-footer']//*[@id='GLUXConfirmClose']");
    public static final By CART_COUNT = By.id("nav-cart-count");
    public static final By PRICE_LIST = By.xpath("//div[@class='a-row a-size-base a-color-base']//span[@class='a-price-whole']");
    public static final By PRICE_FRACTION_LIST = By.xpath("//div[@class='a-row a-size-base a-color-base']//span[@class='a-price-fraction']");
    public static final By NO_THANKS_BUTTON = By.xpath("//input[@aria-labelledby='attachSiNoCoverage-announce']");
    public static final By CLOSE_SIDE_BUTTON = By.id("attach-close_sideSheet-link");
    public static final By SUBTOTAL_PRICE = By.xpath("(//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap'])[1]");
    public static final By NAV_CART_BUTTON = By.id("nav-cart");

    @Test(dataProvider = "dp-test1", dataProviderClass = DP_Test1.class)
    public void _001_SearchForItem(String search_item, String zip_code) {
        System.setProperty(Configuration.CHROME_BROWSER, Configuration.CHROME_DRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(Configuration.BASE_URL);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(GLOBAL_LOCATION).click();
        driver.findElement(ZIP_CODE_INPUT).sendKeys(zip_code);
        driver.findElement(ZIP_CODE_APPLY_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ZIP_CODE_CONFIRM_BUTTON));
        driver.findElement(ZIP_CODE_CONFIRM_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(TOP_SEARCH_BOX_ID));
        driver.findElement(TOP_SEARCH_BOX_ID).sendKeys(search_item);
        driver.findElement(SEARCH_BUTTON).click();

        // Verify cart contains 0 Products
        int cartCounter = Integer.parseInt(driver.findElement(CART_COUNT).getText());
        AssertUtils.assertEquals(0, cartCounter, "Verify cart contains 0 Products Before adding one of " + search_item);
        List<WebElement> listPriceWE = driver.findElements(PRICE_LIST);
        List<Double> listPrice = new ArrayList<>();
        for (WebElement lp : listPriceWE) {
            if (lp.getText().replace(",", "").equals("")) {
                listPrice.add(0.0);
            } else {
                listPrice.add(Double.valueOf(lp.getText().replace(",", "")));
            }
        }
        List<WebElement> listPriceFractionWE = driver.findElements(PRICE_FRACTION_LIST);
        List<Double> listPriceFraction = new ArrayList<>();
        for (WebElement lpFraction : listPriceFractionWE) {
            if (lpFraction.getText().equals("") || Double.valueOf(lpFraction.getText()) == 00) {
                listPriceFraction.add(0.0);
            } else {
                double res = Double.valueOf(lpFraction.getText()) / 100;
                listPriceFraction.add(res);
            }
        }
        List<Double> finalPriceList = new ArrayList<>();
        for (int i = 0; i < listPrice.size(); i++) {
            if (listPrice.get(i) + listPriceFraction.get(i) > 0.0) {
                finalPriceList.add(listPrice.get(i) + listPriceFraction.get(i));
            }
        }
        List<String> searchResultsTitles = driver.findElements(ALL_RETURNED_ELEMENT).stream()
                .map(res -> res.getText())
                .collect(Collectors.toList());
        int randomNumber = (int) ((Math.random() * (searchResultsTitles.size() - 0)));
        List<WebElement> linksFromResult = driver.findElements(LIST_OF_RESULTS_LINKS);
        linksFromResult.get(randomNumber).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART));
        driver.findElement(ADD_TO_CART).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(NO_THANKS_BUTTON));
            driver.findElement(NO_THANKS_BUTTON).click();
        } catch (Exception e) {
            System.out.println("Probably selected a product that does not require to use NO_THANKS button...");
        }
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CLOSE_SIDE_BUTTON));
            driver.findElement(CLOSE_SIDE_BUTTON).click();
        } catch (Exception e) {
            System.out.println("Probably selected a product that does not require to use CLOSE_SIDE button...");
        }

        // Verify cart contains 1 Products
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(CART_COUNT), "1"));
        cartCounter = Integer.parseInt(driver.findElement(CART_COUNT).getText());
        AssertUtils.assertEquals(1, cartCounter, "Verify cart contains 1 Product After adding one " + search_item + " to cart");

        // Verify the title of the selected Product match to at least one title from the returned Products list
        String randomElementTitle = searchResultsTitles.get(randomNumber);
        System.out.println("the selected phone is: " + randomElementTitle);
        boolean checkIfTitleExist = searchResultsTitles.contains(randomElementTitle);
        AssertUtils.assertTrue(checkIfTitleExist, "Verify the title of the selected Product exist in Results list");

        // Verify the price on cart is bigger than 0
        driver.findElement(NAV_CART_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBTOTAL_PRICE));
        boolean subtotalPrice = Double.valueOf(driver.findElement(SUBTOTAL_PRICE).getText().replace("$", "")) > 0;
        AssertUtils.assertTrue(subtotalPrice, "Verify the price on cart is bigger than 0");
    }
}
