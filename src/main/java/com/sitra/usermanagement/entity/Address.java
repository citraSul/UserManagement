package com.sitra.usermanagement.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mobile_phone  ", nullable = false)
    private String mobilePhone;

    @Column(name = "other_phone   ", nullable = false)
    private String otherPhone;

    @Column(name = "location ", nullable = false)
    private String location;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "street_address2", nullable = false)
    private String streetAddress2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode = "US";

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public Address(){

    }
    public Address(Integer id, String mobilePhone, String otherPhone, String location, String streetAddress, String streetAddress2, String city, String state, String postalCode, String countryCode, User user) {
        this.id = id;
        this.mobilePhone = mobilePhone;
        this.otherPhone = otherPhone;
        this.location = location;
        this.streetAddress = streetAddress;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
