package com.example.seminar_rfid.DAO;

import com.example.seminar_rfid.model.BorrowDetailModel;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BorrowDetailDAO {
    MyConnectUnit connect;
    String strTableName = "borrow_detail";

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<BorrowDetailModel> readDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        ResultSet result = this.connect.Select(strTableName, condition, orderBy);
        ArrayList<BorrowDetailModel> DTOs = new ArrayList<>();
        while (result.next()) {
            BorrowDetailModel BorrowDetailModel = new BorrowDetailModel();
            BorrowDetailModel.setBorrowID(result.getString("borrow_id"));
            BorrowDetailModel.setBookID(result.getString("book_id"));

            DTOs.add(BorrowDetailModel);
        }
        connect.Close();
        return DTOs;
    }

    public ArrayList<BorrowDetailModel> readDB(String condition) throws Exception {
        return readDB(condition, null);
    }

    public ArrayList<BorrowDetailModel> readDB() throws Exception {
        return readDB(null);
    }
}
