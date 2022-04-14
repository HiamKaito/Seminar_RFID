package com.example.seminar_rfid;

import com.example.seminar_rfid.BUS.BookBUS;
import com.example.seminar_rfid.BUS.BorrowBUS;
import com.example.seminar_rfid.BUS.BorrowDetailBUS;
import com.example.seminar_rfid.model.BorrowModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.w3c.dom.Entity;

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
            List<String> listBookInBorrow = borrowDetailBUS.countBookInBorrow(model.getBorrowID());
            // search all book in borrow
            for (String s : listBookInBorrow) {
                // get book name
                String bookName = bookBUS.getBookInfor(s).getBookTitle();

                // incr count when it already has in hash map
                if (hashMap.containsValue(bookName)) {
                    for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                        if (entry.getValue().equals(bookName)) {
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
            PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart.getData().add(slice);
        }

        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 12 arial;");

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());

                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }
    }
}
