package com.example.seminar_rfid.BUS;

import com.example.seminar_rfid.DAO.BookDAO;
import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.BorrowModel;

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
            if (model.getBookID().equals(idBook)) {
                return model;
            }
        }

        return null;
    }


    public Boolean update(BookModel DTO) throws Exception {
        if (bookDAO.update(DTO)) {

            // duyệt từng phẩn tử
            for (BookModel dto : bookModelsArrayList) {
                if (dto.getBookID().equals(DTO.getBookID())) {
                    dto.setBookStatus(DTO.getBookStatus());
                    return true;
                }
            }
        }

        return false;
    }
}
