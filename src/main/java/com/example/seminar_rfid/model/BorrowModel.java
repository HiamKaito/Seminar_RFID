package com.example.seminar_rfid.model;

import java.sql.Timestamp;
import java.util.Date;

public class BorrowModel {
    private String borrowID;
    private String userID;
    private Timestamp borrow_beginDate;
    private Timestamp borrow_endDate;
    private Timestamp borrow_returnDate;
    private int borrowStatus;

    public BorrowModel(String borrowID, String userID, Timestamp borrow_beginDate, Timestamp borrow_endDate, Timestamp borrow_returnDate, int borrowStatus) {
        this.borrowID = borrowID;
        this.userID = userID;
        this.borrow_beginDate = borrow_beginDate;
        this.borrow_endDate = borrow_endDate;
        this.borrow_returnDate = borrow_returnDate;
        this.borrowStatus = borrowStatus;
    }

    public BorrowModel() {
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

    public Timestamp getBorrow_beginDate() {
        return borrow_beginDate;
    }

    public void setBorrow_beginDate(Timestamp borrow_beginDate) {
        this.borrow_beginDate = borrow_beginDate;
    }

    public Timestamp getBorrow_endDate() {
        return borrow_endDate;
    }

    public void setBorrow_endDate(Timestamp borrow_endDate) {
        this.borrow_endDate = borrow_endDate;
    }

    public Timestamp getBorrow_returnDate() {
        return borrow_returnDate;
    }

    public void setBorrow_returnDate(Timestamp borrow_returnDate) {
        this.borrow_returnDate = borrow_returnDate;
    }

    public int getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(int borrowStatus) {
        this.borrowStatus = borrowStatus;
    }
}
