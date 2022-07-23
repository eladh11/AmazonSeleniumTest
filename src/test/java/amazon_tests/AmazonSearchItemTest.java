package amazon_tests;

import data_providers.DP_Test1;
import org.testng.annotations.Test;
import pages.AmazonMainPage;
import pages.AmazonSearchResultPage;

public class AmazonSearchItemTest extends AbstractTest {

    @Test(dataProvider = "dp-test1", dataProviderClass = DP_Test1.class)
    public void searchForItemAndAddToCartTest(String item_name, String zip_code) {

        setupAmazonWebsite();

        AmazonMainPage amazonMainPage = new AmazonMainPage(driver);
        amazonMainPage.changeZipCodeToUSA(zip_code).typeToTopSearchBox(item_name);

        AmazonSearchResultPage amazonSearchResultPage = new AmazonSearchResultPage(driver);
        amazonSearchResultPage.verifyCartContainZeroProduct().verifyCartContainOneProduct();
    }
}