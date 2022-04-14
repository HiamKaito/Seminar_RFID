package com.example.seminar_rfid.BUS;

import com.example.seminar_rfid.DAO.BookDAO;
import com.example.seminar_rfid.DAO.BorrowDAO;
import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.BorrowModel;

import java.util.ArrayList;

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


}
