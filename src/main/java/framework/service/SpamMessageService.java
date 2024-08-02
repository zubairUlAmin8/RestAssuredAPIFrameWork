package framework.service;

import com.fasterxml.jackson.core.type.TypeReference;
import framework.model.Message;
import framework.model.TestDataModel;
import framework.model.User;
import framework.model.error.ValidationError;
import framework.utils.common.RestUtil;
import framework.utils.exceptions.AutomationException;
import framework.utils.globalConstants.APIEndPoint;
import framework.utils.globalConstants.HttpStatus;
import framework.utils.reportManagement.extent.ExtentTestManager;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.json.simple.JSONObject;

import java.util.Hashtable;
import java.util.List;


public class SpamMessageService {

    private final Logger _logger = LogManager.getLogger(SpamMessageService.class);

    private User requestPayload;
    private Message messageRequestPayload;

    private Object responsePayload;
    private boolean isNegativeTest = false;
    private HttpStatus httpStatus = HttpStatus.OK;
    private ContentType responseContentType = ContentType.JSON;
    private ContentType requestContentType = ContentType.JSON;

    public static SpamMessageService init() {
        return new SpamMessageService();
    }

    public SpamMessageService isNegativeTest(HttpStatus httpStatus) {
        this.responseContentType = ContentType.JSON;
        this.isNegativeTest = true;
        this.httpStatus = httpStatus;
        return this;
    }

    public SpamMessageService getMessage(Hashtable<String, String> data) throws AutomationException {

        ExtentTestManager.step(_logger, "Spam Review Test");

        System.out.println("Read Data File: "+data.get(TestDataModel.getMessage()));
        Message message= new Message();
        message.setMessage(data.get(TestDataModel.getMessage()).trim());
        messageRequestPayload=message;
        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.MESSAGE_PERDICTION)
                        .contentType(requestContentType)
                        .body(messageRequestPayload)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .post();
        String response= restInstance.getApiResponseAsString();
        System.out.println(response);
        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = null;
        try {
            jsonResponse = (JSONObject) parser.parse(response);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Extracting the prediction value from the JSON response
        String prediction = (String) jsonResponse.get("prediction");

        if(data.get(TestDataModel.getLabel()).equalsIgnoreCase("1")){
            Assert.assertEquals(prediction, "Spam", "The prediction should be 'Not Spam'");
        }else
        {
            Assert.assertEquals(prediction, "Not Spam", "The prediction should be 'Spam'");
        }



        if (!isNegativeTest) {
            responsePayload = restInstance.responseToPojo(new TypeReference<List<Message>>() {});
        } else {
            responsePayload = restInstance.responseToPojo(ValidationError.class);
        }
        return this;
    }


}
