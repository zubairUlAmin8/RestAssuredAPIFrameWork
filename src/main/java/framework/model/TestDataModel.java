package framework.model;

public class TestDataModel {
    public static String message = "MESSAGE";
    public static String label="LABEL";

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        TestDataModel.message = message;
    }



    public static String getLabel() {
        return label;
    }

    public static void setLabel(String label) {
        TestDataModel.label = label;
    }


}
