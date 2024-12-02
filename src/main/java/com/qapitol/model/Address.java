package com.qapitol.model;

public class Address {
    private String streetName;
    private String completeAddress;
    private String pincode;

    public Address(){

    }

    public Address(String streetName, String completeAddress, String pincode) {
        this.streetName = streetName;
        this.completeAddress = completeAddress;
        this.pincode = pincode;
    }
    public String getStreeName() {
        return streetName;
    }
    public void setStreeName(String streeName) {
        this.streetName = streeName;
    }
    public String getCompleteAddr() {
        return completeAddress;
    }
    public void setCompleteAddr(String completeAddr) {
        this.completeAddress = completeAddr;
    }
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    @Override
    public String toString() {
        return "Address [streeName=" + streetName + ", completeAddr=" + completeAddress + ", pincode=" + pincode + "]";
    }
}
