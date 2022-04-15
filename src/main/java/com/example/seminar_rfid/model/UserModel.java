package com.example.seminar_rfid.model;

public class UserModel {
    private String user_id;
    private String user_name;
    private String user_phone;
    private String user_loginname;
    private String user_password;

    public UserModel(String user_id, String user_name, String user_phone, String user_loginname, String user_password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_loginname = user_loginname;
        this.user_password = user_password;
    }

    public UserModel(){
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_loginname() {
        return user_loginname;
    }

    public void setUser_loginname(String user_loginname) {
        this.user_loginname = user_loginname;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
