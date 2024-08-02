package framework.model;


public class Message {

    private String message;
    private String prediction;

    public Message() {

    }

    public Message(String uniqueid) {
        this.message = "message" + uniqueid;
    }

    public String getMessage() {
        return message;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
