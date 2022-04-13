package com.example.seminar_rfid.BUS;

import com.example.seminar_rfid.DAO.BookDAO;
import com.example.seminar_rfid.DAO.ReadDAO;
import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.ReadModel;

import java.util.ArrayList;

public class ReadBUS {
    private ArrayList<ReadModel> readModelsArrayList;
    /**
     * Xử lý các lệnh trong SQL
     */
    private com.example.seminar_rfid.DAO.ReadDAO ReadDAO;

    public ReadBUS() throws Exception {
        readModelsArrayList = new ArrayList<>();
        ReadDAO = new ReadDAO();
        readModelsArrayList = ReadDAO.readDB();
    }

    public ArrayList<ReadModel> getReadInfByID() {

        return readModelsArrayList;
    }

    public String getBookID(String rfid) {
        for (ReadModel model : readModelsArrayList) {
            if (rfid.equals(model.getTagRFID())) {
                return model.getBookID();
//                return model;
            }
        }

        return null;
    }
}
