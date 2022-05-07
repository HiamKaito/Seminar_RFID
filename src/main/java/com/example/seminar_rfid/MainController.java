package com.example.seminar_rfid;

import com.example.seminar_rfid.BUS.BookBUS;
import com.example.seminar_rfid.BUS.BorrowBUS;
import com.example.seminar_rfid.BUS.BorrowDetailBUS;
import com.example.seminar_rfid.BUS.ReadBUS;
import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.BorrowModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;

public class MainController implements Initializable {
    @FXML
    protected TableView  tbl_rfid;
    @FXML
    Button btnConfim, btn_Login, btnScan;


    private final ObservableList<BookModel> RFIDData =
            FXCollections.observableArrayList();
    private final ObservableList<BookModel> BookData =
            FXCollections.observableArrayList();
    private final ObservableList<BorrowModel> BorrowData =
            FXCollections.observableArrayList();

    ArrayList<BookModel> RFIDModel = new ArrayList<>();
    ArrayList<BookModel> bookModel = new ArrayList<>();


    BookBUS bookBus;
    ReadBUS readBUS;

    BorrowDetailBUS borrowDetailBUS;

    Read readRFID = new Read();
    public Timer countDown = new Timer();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnConfim.setOnAction(this::btnConfirm);
        btn_Login.setOnAction(this::setBtn_Login);
        btnScan.setOnAction(this::ScanRFID);

        try {
            bookBus = new BookBUS();
            readBUS = new ReadBUS();
            bookModel = bookBus.getBookInfByID();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //book ID - RFID ID
        TableColumn bookID = new TableColumn("Book ID");
        bookID.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookID"));

        TableColumn bookTitle = new TableColumn("Book Title");
        bookTitle.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookTitle"));

        TableColumn bookAuthor = new TableColumn("Book Author");
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookAuthor"));

        //borrow Status - 0: registered, 1: received, 2: returned
        TableColumn bookStatus = new TableColumn("Book Status");
        bookStatus.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("BookStatus"));

        tbl_rfid.setItems(RFIDData);
        tbl_rfid.getColumns().addAll(bookID, bookTitle, bookAuthor, bookStatus);

        //tbl_book.setItems(BookData);
        // tbl_book.getColumns().addAll(bookID, bookTitle, bookAuthor, bookStatus);

        //handle double click on row
//        tbl_book.setRowFactory(tv -> {
//            TableRow<BookModel> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 2 && (!row.isEmpty())) {
//                    BookModel rowData = row.getItem();
//                    System.out.println("Double click on: " + rowData.getBookID());
//                    //Open new Stage of Book Log
//                    try {
//                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BookLog.fxml"));
//                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//                        Stage stage = new Stage();
//                        stage.initModality(Modality.APPLICATION_MODAL);
//                        stage.setTitle("BookLog!");
//                        BookLogController mainCtl = fxmlLoader.getController();
//                        //Pass whatever data you want. You can have multiple method calls here
//                        mainCtl.transferMessage(rowData.getBookID());
//                        stage.setScene(scene);
//                        stage.show();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            return row;
//        });
    }

    private void ScanRFID(ActionEvent actionEvent) {
        RFIDData.clear();
        RFIDModel.clear();
        Memory.refeshList();

        // search itemRFID
        readRFID.searchItem();

        // get all RFID above and
        for (String s : readRFID.idRFID) {
            if (bookBus.getBookInfor(readBUS.getBookID(s)).getBookStatus().equals("Received")) {
                // add all id book to memory
                String idBook = readBUS.getBookID(s);
                Memory.addToListIdBook(
                        bookBus.getBookInfor(idBook).getBookID()
                );

                // in ra ngoai table tbl rfid
                if (!RFIDModel.contains(bookBus.getBookInfor(idBook))) {
                    RFIDModel.add(
                            bookBus.getBookInfor(idBook)
                    );
                }
            }

            // escape for
            RFIDData.addAll(RFIDModel);
        }


        readRFID.idRFID = new ArrayList<>();

    }

    private void btnConfirm(ActionEvent actionEvent) {
        try {
            if (Memory.getCountList() > 0) {
                countDown.cancel();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ConfirmBooks.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Login!");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setBtn_Login(ActionEvent event) {
        try {
            countDown.cancel();

            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("Login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

