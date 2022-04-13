package com.example.seminar_rfid.BUS;

import com.example.seminar_rfid.DAO.BookDAO;
import com.example.seminar_rfid.model.BookModel;

import java.util.ArrayList;

public class BookBUS {
    private ArrayList<BookModel> bookModelsArrayList;
    /**
     * Xử lý các lệnh trong SQL
     */
    private BookDAO bookDAO;

    public BookBUS() throws Exception {
        bookModelsArrayList = new ArrayList<>();
        bookDAO = new BookDAO();
        bookModelsArrayList = bookDAO.readDB();
    }

    public ArrayList<BookModel> getBookInfByID() {

        return bookModelsArrayList;
    }

    public BookModel getBookInfor(String idBook) {
        for (BookModel model : bookModelsArrayList) {
//            if (idBook.equals(model.getBookID())) {
            if (model.getBookID().equals(idBook)) {
                System.out.println(model.getBookID());
                return model;
            }
        }

        return null;
    }
}
