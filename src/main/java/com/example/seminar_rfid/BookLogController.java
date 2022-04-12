package com.example.seminar_rfid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class BookLogController implements Initializable {
    @FXML
    private Label BookLogID;
    @FXML
    private TableView tbl_bookLog;

    private final ObservableList<BorrowModel> BorrowData =
            FXCollections.observableArrayList(
                    new BorrowModel("11111111", "ID1", new Date(), new Date(), new Date(), 1),
                    new BorrowModel("22222222", "ID2", new Date(), new Date(), new Date(), 2)
            );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn borrowID = new TableColumn("Borrow ID");
        borrowID.setCellValueFactory(new PropertyValueFactory<BorrowModel, String >("borrowID"));

        TableColumn userID = new TableColumn("User ID");
        userID.setCellValueFactory(new PropertyValueFactory<BorrowModel, String >("userID"));

        TableColumn borrow_beginDate = new TableColumn("BeginDate");
        borrow_beginDate.setCellValueFactory(new PropertyValueFactory<BorrowModel, String >("borrow_beginDate"));

        TableColumn borrow_endDate = new TableColumn("EndDate");
        borrow_endDate.setCellValueFactory(new PropertyValueFactory<BorrowModel, String >("borrow_endDate"));

        TableColumn borrow_returnDate = new TableColumn("ReturnDate");
        borrow_returnDate.setCellValueFactory(new PropertyValueFactory<BorrowModel, String >("borrow_returnDate"));

        TableColumn borrowStatus = new TableColumn("Borrow Status");
        borrowStatus.setCellValueFactory(new PropertyValueFactory<BorrowModel, String >("borrowStatus"));

        tbl_bookLog.setItems(BorrowData);
        tbl_bookLog.getColumns().addAll(borrowID, userID, borrow_beginDate, borrow_endDate, borrow_returnDate, borrowStatus);
    }
    public void transferMessage(String message) {
        ////Receive message from scene 1 and display
        BookLogID.setText("Book ID: " + message);
    }
}
