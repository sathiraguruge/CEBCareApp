package com.example.cebcareapp.Entity;

public class Payment {
    private String date;
    private String accountNumber;
    private String name;
    private String email;
    private String amount;
    private String paymentMethod;


    private String image;

    public Payment() {
    }


    public Payment(String date, String accountNumber, String name, String email, String amount, String paymentMethod, String image) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.name = name;
        this.email = email;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
