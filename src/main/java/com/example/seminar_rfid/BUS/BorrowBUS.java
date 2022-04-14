package com.example.seminar_rfid.BUS;

import com.example.seminar_rfid.DAO.BookDAO;
import com.example.seminar_rfid.DAO.BorrowDAO;
import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.BorrowModel;

import java.util.ArrayList;
import java.util.List;

public class BorrowBUS {
    public ArrayList<BorrowModel> bookModelsArrayList;
    /**
     * Xử lý các lệnh trong SQL
     */
    private BorrowDAO DAO;

    public BorrowBUS() throws Exception {
        bookModelsArrayList = new ArrayList<>();
        DAO = new BorrowDAO();
        bookModelsArrayList = DAO.readDB();
    }

    public ArrayList<BorrowModel> getBookInfo() {
        return bookModelsArrayList;
    }

    public BorrowModel getBookInfByID(String borrowID){
        for(BorrowModel model : bookModelsArrayList){
            if(model.getBorrowID().equals(borrowID)){
                return model;
            }
        }
        return null;
    }

    public void getAllByDate(String date_1, String date_2) {
        try {
            bookModelsArrayList = DAO.readDB("`borrow_begindate` >= '"+date_1+
                    "' AND `borrow_enddate` <= '"+date_2+"' AND `borrow_status` = 2");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
