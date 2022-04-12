package com.example.seminar_rfid.model;

public class BookModel {
    private String BookID;
    private String BookAuthor;
    private String BookStatus;
    private String BookTitle;

    public BookModel(String bookID, String bookAuthor, String bookStatus, String bookTitle) {
        BookID = bookID;
        BookAuthor = bookAuthor;
        BookStatus = bookStatus;
        BookTitle = bookTitle;
    }

    public BookModel() {

    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        BookAuthor = bookAuthor;
    }

    public String getBookStatus() {
        if(BookStatus.equalsIgnoreCase("0")) {
            BookStatus = "Registered";
        }
        if(BookStatus.equalsIgnoreCase("1")) {
            BookStatus = "Received";
        }
        if(BookStatus.equalsIgnoreCase("2")) {
            BookStatus = "Returned";
        }
        return BookStatus;
    }

    public void setBookStatus(String bookStatus) {
        BookStatus = bookStatus;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }
}
