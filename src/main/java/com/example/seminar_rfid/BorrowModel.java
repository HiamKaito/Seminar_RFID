package com.example.seminar_rfid;

import java.util.Date;

public class BorrowModel {
    private String borrowID;
    private String userID;
    private Date borrow_beginDate;
    private Date borrow_endDate;
    private Date borrow_returnDate;
    private int borrowStatus;

    public BorrowModel(String borrowID, String userID, Date borrow_beginDate, Date borrow_endDate, Date borrow_returnDate, int borrowStatus) {
        this.borrowID = borrowID;
        this.userID = userID;
        this.borrow_beginDate = borrow_beginDate;
        this.borrow_endDate = borrow_endDate;
        this.borrow_returnDate = borrow_returnDate;
        this.borrowStatus = borrowStatus;
    }

    public String getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getBorrow_beginDate() {
        return borrow_beginDate;
    }

    public void setBorrow_beginDate(Date borrow_beginDate) {
        this.borrow_beginDate = borrow_beginDate;
    }

    public Date getBorrow_endDate() {
        return borrow_endDate;
    }

    public void setBorrow_endDate(Date borrow_endDate) {
        this.borrow_endDate = borrow_endDate;
    }

    public Date getBorrow_returnDate() {
        return borrow_returnDate;
    }

    public void setBorrow_returnDate(Date borrow_returnDate) {
        this.borrow_returnDate = borrow_returnDate;
    }

    public int getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(int borrowStatus) {
        this.borrowStatus = borrowStatus;
    }
}
