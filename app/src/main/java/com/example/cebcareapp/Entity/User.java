package com.example.cebcareapp.Entity;

public class User {

    private int ID;
    private String fullName;
    private String email;
    private Integer phone;
    private Integer landPhone;
    private String userName;
    private String password;

    public User() {
    }



    public User(String fullName, String email, Integer phone, Integer landPhone, String userName, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.landPhone = landPhone;
        this.userName = userName;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getLandPhone() {
        return landPhone;
    }

    public void setLandPhone(Integer landPhone) {
        this.landPhone = landPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
