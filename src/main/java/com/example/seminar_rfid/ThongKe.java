package com.example.seminar_rfid;

import com.example.seminar_rfid.BUS.BookBUS;
import com.example.seminar_rfid.BUS.BorrowBUS;
import com.example.seminar_rfid.BUS.BorrowDetailBUS;
import com.example.seminar_rfid.model.BorrowModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ThongKe implements Initializable {
    @FXML
    Button btn;
    @FXML
    DatePicker date_1, date_2;
    @FXML
    ComboBox cbb;
    @FXML
    PieChart pieChart;

    BorrowBUS borrowBUS;
    BookBUS bookBUS;
    BorrowDetailBUS borrowDetailBUS;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //btn
        btn.setOnAction(this::btnClick);
    }

    private void btnClick(ActionEvent actionEvent) {
        LocalDate localDate_1 = date_1.getValue();
        LocalDate localDate_2 = date_2.getValue();

        System.out.println(localDate_1);
        System.out.println(localDate_2);

        chartBook(String.valueOf(localDate_1), String.valueOf(localDate_2));
    }

    private void chartBook(String date_1, String date_2) {
        try {
            borrowBUS = new BorrowBUS();
            bookBUS = new BookBUS();
            borrowDetailBUS = new BorrowDetailBUS();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        pieChart.getData().clear();

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        borrowBUS.getAllByDate(date_1, date_2);

        for (BorrowModel model : borrowBUS.bookModelsArrayList) {
            List<String> listBookInBorrow = borrowDetailBUS.countBookInBorrow(model.getBorrowID());
            // search all book in borrow
            for (String s : listBookInBorrow) {
                // get book name
                String bookName = bookBUS.getBookInfor(s).getBookTitle();

                // incr count when it already has in hash map
                if (hashMap.containsKey(bookName)) {
                    for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                        if (entry.getKey().equals(bookName)) {
                            int value = entry.getValue() + 1;
                            hashMap.put(bookName, value);
                            break;
                        }
                    }
                } else {
                    // add to hash map
                    hashMap.put(bookName, 1);
                }

            }


        }


        // add all value to chart
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart.getData().add(slice);
        }


    }
}
