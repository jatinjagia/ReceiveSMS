package com.example.jatin.receivesms;

/**
 * Created by jatin on 29/9/17.
 */

public class Details {

    private String carrierName,message,phoneNumber;

    public Details(String carrierName,String message,String phoneNumber){
        this.carrierName=carrierName;
        this.message=message;
        this.phoneNumber=phoneNumber;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
