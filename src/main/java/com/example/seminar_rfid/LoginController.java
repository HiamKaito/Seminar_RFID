package com.example.seminar_rfid;

import com.example.seminar_rfid.BUS.UserBUS;
import com.example.seminar_rfid.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    TextField tf_username;
    @FXML
    PasswordField pf_password;
    @FXML
    Button btn_login;

    UserBUS userBUS = new UserBUS();

    public LoginController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_login.setOnAction(this::setBtn_login);


    }

    private void setBtn_login(ActionEvent actionEvent) {
        if (!tf_username.getText().equals("") && !pf_password.getText().equals("") && tf_username.getText().equals("admin")) {
            try {
                if (userBUS.kiemTraDangNhap(tf_username.getText(), pf_password.getText())) {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("ThongKe.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Sai username or pass");
        }
    }
}
