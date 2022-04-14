package com.example.seminar_rfid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ThongKe.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
