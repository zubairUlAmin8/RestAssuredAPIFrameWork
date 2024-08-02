/*
 * Copyright (c) 2022. Anh Tester
 * Automation Framework Selenium
 */

package dataprovider;



import framework.helpers.ExcelHelpers;
import org.testng.annotations.DataProvider;

import java.util.Hashtable;

public final class DataProviderManager {


//    @Test(dataProvider = "getSignUpDataHashTable" )


//    @Test(dataProvider = "getClientDataHashTable")

    @DataProvider(name = "getReviewDataHashTable", parallel = false)
    public static Object[][] getSignInData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable("src/main/resources/spamTestData.xlsx", "Sheet1", 1, 8);
        System.out.println("getReviewDataHashTable: " + data);
        return data;
    }

}
