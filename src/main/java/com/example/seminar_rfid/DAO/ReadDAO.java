package com.example.seminar_rfid.DAO;

import com.example.seminar_rfid.model.ReadModel;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReadDAO {
    MyConnectUnit connect;
    String strTableName = "tag_read";

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<ReadModel> readDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        ResultSet result = this.connect.Select(strTableName, condition, orderBy);
        ArrayList<ReadModel> DTOs = new ArrayList<>();
        while (result.next()) {
            ReadModel ReadModel = new ReadModel();
            ReadModel.setTagRFID(result.getString("tag_rfid"));
            ReadModel.setBookID(result.getString("book_id"));
//            ReadModel.set(result.getString("tag_time"));
            DTOs.add(ReadModel);
        }
        connect.Close();
        return DTOs;
    }

    public ArrayList<ReadModel> readDB(String condition) throws Exception {
        return readDB(condition, null);
    }

    public ArrayList<ReadModel> readDB() throws Exception {
        return readDB(null);
    }
}
