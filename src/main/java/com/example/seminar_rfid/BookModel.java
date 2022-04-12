package com.example.seminar_rfid;

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
