package com.example.seminar_rfid.DAO;

import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.BorrowModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class BookDAO {
    MyConnectUnit connect;
    String strTableName = "books";

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<BookModel> readDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        ResultSet result = this.connect.Select(strTableName, condition, orderBy);
        ArrayList<BookModel> DTOs = new ArrayList<>();
        while (result.next()) {
            BookModel bookModel = new BookModel();
            bookModel.setBookID(result.getString("book_id"));
            bookModel.setBookTitle(result.getString("book_title"));
            bookModel.setBookStatus(result.getString("book_status"));
            bookModel.setBookAuthor(result.getString("book_author"));
            DTOs.add(bookModel);
        }
        connect.Close();
        return DTOs;
    }

    public ArrayList<BookModel> readDB(String condition) throws Exception {
        return readDB(condition, null);
    }

    public ArrayList<BookModel> readDB() throws Exception {
        return readDB(null);
    }

    public Boolean update(BookModel model) throws Exception {
        connect = new MyConnectUnit();

        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
//        insertValues.put("book_id", model.getBookID());
        insertValues.put("book_title", model.getBookTitle());
        insertValues.put("book_status", 2);
        insertValues.put("book_author", model.getBookAuthor());


        String condition = " book_id = '" + model.getBookID() + "'";

        Boolean check = connect.Update(strTableName, insertValues, condition);

        connect.Close();
        return check;
    }
}
