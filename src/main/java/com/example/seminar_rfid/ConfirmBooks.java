package com.example.seminar_rfid;

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
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ConfirmBooks implements Initializable {
    @FXML
    Button btnDung, btnKhong;
    @FXML
    Label lblTraSach;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTraSach.setText("Bạn muốn trả " + Memory.getCountList() + " sách phải không?");
        btnDung.setOnAction(this::btnDung);
        btnKhong.setOnAction(this::btnKhong);
    }

    private void btnKhong(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("ConfirmBooks.fxml"));
            Parent root = null;
            root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void btnDung(ActionEvent actionEvent) {
        lblTraSach.setText("Trả sách thành công, cảm ơn.");
        btnDung.setVisible(false);
        btnKhong.setText("Trở về");

        //TODO m ngon thi m lam di @T.Anh
        //TODO them vao database



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
