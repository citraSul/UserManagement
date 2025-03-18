package com.sitra.usermanagement.dto;


import lombok.Data;


@Data

public class AddressDTO {
    private String mobilePhone;
    private String otherPhone;
    private String location;
    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String state;
    private String postalCode;
    private String countryCode;
    public AddressDTO(){}
    public AddressDTO(String mobilePhone, String otherPhone, String location, String streetAddress, String streetAddress2, String city, String state, String postalCode, String countryCode) {
        this.mobilePhone = mobilePhone;
        this.otherPhone = otherPhone;
        this.location = location;
        this.streetAddress = streetAddress;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
