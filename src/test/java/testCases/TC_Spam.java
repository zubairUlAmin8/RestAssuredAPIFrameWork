package testCases;

import dataprovider.DataProviderManager;
import framework.model.Message;
import framework.service.SpamMessageService;
import framework.utils.exceptions.AutomationException;
import framework.utils.initializers.TestInit;
import framework.utils.reportManagement.extent.ExtentTestManager;
import org.testng.annotations.Test;

import java.util.Hashtable;

/* -----------------------------------------------------------------------
   - ** Rest API Testing Framework using RestAssured **
   ----------------------------------------------------------------------- */

public class TC_Spam extends TestInit {


    @Test(priority = 1,enabled = true, dataProvider = "getReviewDataHashTable", dataProviderClass = DataProviderManager.class)
    public void TC01_VerifySpamMessage(Hashtable<String, String> data) throws AutomationException {
        ExtentTestManager.startTest("Check Spam", "To verify the spam message");
        SpamMessageService
                .init()
                .getMessage(data);
    }
}
