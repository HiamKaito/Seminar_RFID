package com.example.seminar_rfid.DAO;

import com.example.seminar_rfid.model.UserModel;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
    MyConnectUnit connect;
    String strTableName = "users";

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<UserModel> readDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        ResultSet result = this.connect.Select(strTableName, condition, orderBy);
        ArrayList<UserModel> DTOs = new ArrayList<>();
        while (result.next()) {
            UserModel userModel = new UserModel();
            userModel.setUser_id(result.getString("user_id"));
            userModel.setUser_name(result.getString("user_name"));
            userModel.setUser_phone(result.getString("user_phone"));
            userModel.setUser_loginname(result.getString("user_loginname"));
            userModel.setUser_password(result.getString("user_password"));
            DTOs.add(userModel);
        }
        connect.Close();
        return DTOs;
    }

    public ArrayList<UserModel> readDB(String condition) throws Exception {
        return readDB(condition, null);
    }

    public ArrayList<UserModel> readDB() throws Exception {
        return readDB(null);
    }
}
