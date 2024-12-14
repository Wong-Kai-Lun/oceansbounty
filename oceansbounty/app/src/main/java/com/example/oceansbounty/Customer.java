package com.example.oceansbounty;

public class Customer {
    private int id;
    private String name;
    private String phoneNumber;

    // Customer ID
    public int getCustomerID() {
        return id;
    }

    // Customer Name
    public String getCustomerName() {
        return name;
    }
    public void setCustomerName(String newCustomerName) {
        this.name = newCustomerName;
    }

    // Customer Phone Number
    public String getCustomerPhoneNum() {
        return phoneNumber;
    }
    public void setCustomerPhoneNum(String newCustomerPhoneNum) {
        this.phoneNumber = newCustomerPhoneNum;
    }
}
