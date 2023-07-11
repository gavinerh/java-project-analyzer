package MARMSUI;

public class ConvertJsonStringToObject {
    public static void main(String[] args) {
        String s = "{\"status\":\"FAILURE\",\"response\":{\"errorCode\":\"N0140\",\"errorDescription\":\"Customer ID Not found\"}}";
        int statusValueStartIndex = s.indexOf("status") + "status".length() + 3;
        int statusValueEndIndex = s.indexOf("\"", statusValueStartIndex);
        int errorCodeValueStartIndex = s.indexOf("errorCode") + "errorCode".length() + 3;
        int errorCodeValueEndIndex = s.indexOf("\"", errorCodeValueStartIndex);
        int errorDescriptionStartIndex = s.indexOf("errorDescription") + "errorDescription".length() + 3;
        int errorDescriptionEndIndex = s.indexOf("\"", errorDescriptionStartIndex);
        System.out.println(String.format("status: %s", s.substring(statusValueStartIndex,statusValueEndIndex)));
        System.out.println(String.format("errorCode: %s", s.substring(errorCodeValueStartIndex,errorCodeValueEndIndex)));
        System.out.println(String.format("ErrorDescription: %s",s.substring(errorDescriptionStartIndex,errorDescriptionEndIndex)));
    }
}
