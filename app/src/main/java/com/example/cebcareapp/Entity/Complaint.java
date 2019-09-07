package com.example.cebcareapp.Entity;

import android.accounts.Account;

import java.io.Serializable;

public class Complaint implements Serializable {

    private int ID;
    private String account;
    private String complaintType;
    private String complaint;
    private Integer phone;
    private String email;
    private String dateAdded;


    public Complaint() {

    }

    public Complaint(int ID, String account, String complaintType, String complaint, Integer phone, String email, String dateAdded) {
        this.ID = ID;
        this.account = account;
        this.complaintType = complaintType;
        this.complaint = complaint;
        this.phone = phone;
        this.email = email;
        this.dateAdded = dateAdded;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
