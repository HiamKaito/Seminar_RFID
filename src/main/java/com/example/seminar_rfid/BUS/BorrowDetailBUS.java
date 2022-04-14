package com.example.seminar_rfid.BUS;

import com.example.seminar_rfid.DAO.BorrowDAO;
import com.example.seminar_rfid.DAO.BorrowDetailDAO;
import com.example.seminar_rfid.model.BorrowDetailModel;
import com.example.seminar_rfid.model.BorrowModel;

import java.util.ArrayList;
import java.util.List;

public class BorrowDetailBUS {
    public ArrayList<BorrowDetailModel> list;
    /**
     * Xử lý các lệnh trong SQL
     */
    private BorrowDetailDAO DAO;
    BorrowBUS borrowBUS;

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

    public BorrowModel getBorrowId(String bookID){
        for (BorrowDetailModel model : list){
            if(model.getBookID().equals(bookID)){
                return borrowBUS.getBookInfByID(model.getBorrowID());
            }
        }
        return null;
    }

    public List countBookInBorrow(String borrowID) {
        List<String> listRes = new ArrayList<>();

        for (BorrowDetailModel model : list) {
            if (model.getBorrowID().equals(borrowID)) {
//                count++;
                listRes.add(model.getBookID());
            }
        }
        return listRes;
    }

}
