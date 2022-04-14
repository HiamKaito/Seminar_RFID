package com.example.seminar_rfid.model;

import java.sql.Timestamp;

public class BorrowDetailModel {
    private String borrowID;
    private String bookID;

    public BorrowDetailModel() {
    }

    public BorrowDetailModel(String borrowID, String bookID) {
        this.borrowID = borrowID;
        this.bookID = bookID;
    }

    public String getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
