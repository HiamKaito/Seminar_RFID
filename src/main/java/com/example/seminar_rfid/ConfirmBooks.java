package com.example.seminar_rfid;

import com.example.seminar_rfid.BUS.BookBUS;
import com.example.seminar_rfid.BUS.BorrowBUS;
import com.example.seminar_rfid.BUS.BorrowDetailBUS;
import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.BorrowModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ConfirmBooks implements Initializable {
    @FXML
    Button btnDung, btnKhong;
    @FXML
    Label lblTraSach;

    BorrowBUS borrowBUS;
    BorrowDetailBUS borrowDetailBUS;
    BookBUS bookBUS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTraSach.setText("Bạn muốn trả " + Memory.getCountList() + " sách phải không?");
        btnDung.setOnAction(this::btnDung);
        btnKhong.setOnAction(this::btnKhong);
    }

    private void btnKhong(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private void btnDung(ActionEvent actionEvent) {
        lblTraSach.setText("Trả sách thành công, cảm ơn.");
        btnDung.setVisible(false);
        btnKhong.setText("Trở về");


        // read database
        try {
            bookBUS = new BookBUS();
            borrowBUS = new BorrowBUS();
            borrowDetailBUS = new BorrowDetailBUS();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // search and get borrow
        String borrowID = borrowDetailBUS.getBorrowId(Memory.listIdBook.get(0)).getBorrowID();
        BorrowModel borrowModel = borrowBUS.getBorrowModel(borrowID);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        borrowModel.setBorrowStatus(2);
        borrowModel.setBorrow_returnDate(Timestamp.valueOf(timeStamp));

        // change borrow status to 2
        try {
            borrowBUS.update(borrowModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // change all book status to 2
        for (int i = 0 ; i < Memory.getCountList() ; i++) {
            BookModel bookModel = bookBUS.getBookInfor(Memory.listIdBook.get(i));
            System.out.println(bookModel.toString());

            bookModel.setBookStatus("2");

            try {
                bookBUS.update(bookModel);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        Memory.refeshList();

        // change button position
        Timer countDown = new Timer();
        countDown.scheduleAtFixedRate(new TimerTask() {
            int x = 100;

            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (x == 0) {
                        countDown.cancel();
                    } else {
                        x--;
                        btnKhong.setLayoutX(btnKhong.getLayoutX() - 1.0);
                    }
                });
            }
        }, 0, 10);

    }
}
