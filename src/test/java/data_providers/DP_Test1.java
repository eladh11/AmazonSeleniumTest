package data_providers;

import org.testng.annotations.DataProvider;

public class DP_Test1 {
    @DataProvider(name = "dp-test1")
    public Object[][] getData() {
        return new Object[][]{{"Samsung Galaxy", "90011"}};
    }
}
