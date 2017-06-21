package com.cts.customer.vo;


import org.joda.time.DateTime;

public class Customer {

    private long id;
    private String userName;
    private String mailId;
    private String location;
    private String createdDate;

    public Customer(long id, String userName, String mailId, String location, String createdDate) {
        this.id = id;
        this.userName = userName;
        this.mailId = mailId;
        this.location = location;
        this.createdDate = createdDate;
    }

    public Customer(){
    }

    public Customer(String customerName){
        this.userName = customerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
