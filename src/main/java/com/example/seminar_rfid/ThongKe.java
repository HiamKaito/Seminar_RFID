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

        chart();
    }

    private void chart() {
        try {
            borrowBUS = new BorrowBUS();
            bookBUS = new BookBUS();
            borrowDetailBUS = new BorrowDetailBUS();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        pieChart.getData().clear();

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        for (BorrowModel model : borrowBUS.bookModelsArrayList) {
            int numberBookInBorrow = borrowDetailBUS.countBookInBorrow(model.getBorrowID());
            // search all book in borrow
            for (int i = 0 ; i < numberBookInBorrow ; i++) {
                // get book name
                String bookName = bookBUS.getBookInfor(
                        borrowDetailBUS.getBookId(model.getBorrowID())
                ).getBookTitle();

                // incr count when it already has in hash map
                for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
//                    if (entry.getValue().equals())
                }

            }

            PieChart.Data slide = new PieChart.Data( bookName, 1);

            pieChart.getData().add(slide);
            System.out.println("A");
        }

//        PieChart.Data slice1 = new PieChart.Data("USA", 1);
//        pieChart.getData().add(slice1);

    }
}
