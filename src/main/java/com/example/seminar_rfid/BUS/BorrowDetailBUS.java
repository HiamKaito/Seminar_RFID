package com.example.seminar_rfid.BUS;

import com.example.seminar_rfid.DAO.BorrowDAO;
import com.example.seminar_rfid.DAO.BorrowDetailDAO;
import com.example.seminar_rfid.model.BorrowDetailModel;
import com.example.seminar_rfid.model.BorrowModel;

import java.util.ArrayList;

public class BorrowDetailBUS {
    public ArrayList<BorrowDetailModel> list;
    /**
     * Xử lý các lệnh trong SQL
     */
    private BorrowDetailDAO DAO;

    public BorrowDetailBUS() throws Exception {
        list = new ArrayList<>();
        DAO = new BorrowDetailDAO();
        list = DAO.readDB();
    }

    public ArrayList<BorrowDetailModel> getBookInfByID() {
        return list;
    }

    public String getBookId(String borrowID) {
        for (BorrowDetailModel model : list) {
            if (model.getBorrowID().equals(borrowID)){
                return model.getBookID();
            }
        }

        return null;
    }

    public int countBookInBorrow(String borrowID) {
        int count =0 ;
        for (BorrowDetailModel model : list) {
            if (model.getBorrowID().equals(borrowID)) {
                count++;
            }
        }
        return count;
    }
}
