package com.e.patientportal.Model;

public class User {
    private String name,address,mobile,photo;
    public User(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public User(String name, String address, String mobile) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.photo = photo;
    }
}
