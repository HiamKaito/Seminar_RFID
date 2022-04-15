package com.example.seminar_rfid.BUS;

import com.example.seminar_rfid.DAO.UserDAO;
import com.example.seminar_rfid.model.UserModel;

import java.util.ArrayList;

public class UserBUS {
    private ArrayList<UserModel> list_DTO;
    /**
     * Xử lý các lệnh trong SQL
     */
    private UserDAO DAO;

    public UserBUS() throws Exception {
        list_DTO = new ArrayList<>();
        DAO = new UserDAO();
        list_DTO = DAO.readDB();
    }


    /**
     * Kiêm tra xem tài khoản đó có trong arraylist hay chưa <br>
     * <h3> Không phân biệt hoa thường </h3>
     *
     * @return true nếu thành công
     */
    public Boolean kiemTraDangNhap(String userName, String password) {
        for (UserModel model : list_DTO) {
            // kiểm tra mật khảu và tên đăng nhập
            if (model.getUser_loginname().equals(userName)
                    && model.getUser_password().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
