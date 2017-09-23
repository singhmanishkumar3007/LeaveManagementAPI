package com.easybusiness.leavemanagement.dto;

public class LeaveActivityResponse {
    
    private String responseMessage;
    private String responseCode;
    public LeaveActivityResponse() {
	super();
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    @Override
    public String toString() {
	return "LeaveActivityResponse [responseMessage=" + responseMessage + ", responseCode=" + responseCode + "]";
    }
    
    
    
    

}
