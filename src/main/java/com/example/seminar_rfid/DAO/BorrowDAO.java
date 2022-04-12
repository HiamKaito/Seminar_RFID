package com.example.seminar_rfid.DAO;

import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.BorrowModel;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BorrowDAO {
    MyConnectUnit connect;
    String strTableName = "borrow";

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<BorrowModel> readDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        ResultSet result = this.connect.Select(strTableName, condition, orderBy);
        ArrayList<BorrowModel> DTOs = new ArrayList<>();
        while (result.next()) {
            BorrowModel borrowModel = new BorrowModel();
            borrowModel.setBorrowID(result.getString("borrow_id"));
            borrowModel.setBorrow_beginDate(result.getTimestamp("borrow_begindate"));
            borrowModel.setBorrow_endDate(result.getTimestamp("borrow_enddate"));
            borrowModel.setBorrow_returnDate(result.getTimestamp("borrow_returndate"));
            borrowModel.setUserID(result.getString("user_id"));
            borrowModel.setBorrowStatus(result.getInt("borrow_status"));

            DTOs.add(borrowModel);
        }
        connect.Close();
        return DTOs;
    }

    public ArrayList<BorrowModel> readDB(String condition) throws Exception {
        return readDB(condition, null);
    }

    public ArrayList<BorrowModel> readDB() throws Exception {
        return readDB(null);
    }
}
