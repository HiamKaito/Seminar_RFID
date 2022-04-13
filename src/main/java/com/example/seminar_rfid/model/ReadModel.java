package com.example.seminar_rfid.model;

public class ReadModel {
    private String TagRFID;
    private String BookID;


    public ReadModel() {
    }

    public ReadModel(String tagRFID, String bookID) {
        TagRFID = tagRFID;
        BookID = bookID;
    }

    public String getTagRFID() {
        return TagRFID;
    }

    public void setTagRFID(String tagRFID) {
        TagRFID = tagRFID;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }
}
