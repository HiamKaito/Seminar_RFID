package com.example.seminar_rfid;

import com.example.seminar_rfid.BUS.BookBUS;
import com.example.seminar_rfid.BUS.BorrowBUS;
import com.example.seminar_rfid.BUS.BorrowDetailBUS;
import com.example.seminar_rfid.BUS.ReadBUS;
import com.example.seminar_rfid.model.BookModel;
import com.example.seminar_rfid.model.BorrowModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements Initializable {
    @FXML
    protected TableView tbl_book, tbl_rfid;
    private final ObservableList<BookModel> RFIDData =
            FXCollections.observableArrayList();
    private final ObservableList<BookModel> BookData =
            FXCollections.observableArrayList();
    private final ObservableList<BorrowModel> BorrowData =
            FXCollections.observableArrayList();

    ArrayList<BookModel> readModel = new ArrayList<>();
    ArrayList<BookModel> bookModel = new ArrayList<>();
    ArrayList<BorrowModel> borrowModel = new ArrayList<>();


    BookBUS bookBus;
    ReadBUS readBUS;
    BorrowBUS borrowBUS;
    BorrowDetailBUS borrowDetailBUS;

    Read readRFID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbl_book.setEditable(true);
        try {
            bookBus = new BookBUS();
            readBUS = new ReadBUS();
            borrowBUS = new BorrowBUS();
            borrowDetailBUS = new BorrowDetailBUS();

            bookModel = bookBus.getBookInfByID();

            Timer countDown = new Timer();
            countDown.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    RFIDData.clear();
                    readModel.clear();
                    borrowModel.clear();

                    readRFID.searchItem();
//                    displayInfor();
                    for (String s : readRFID.idRFID) {
                        System.out.println(s);
                        readModel.add(
                                bookBus.getBookInfor(readBUS.getBookID(s))
                        );
                        borrowModel.add(
                                borrowDetailBUS.getBorrowId(readBUS.getBookID(s))
                        );
                    }
                    RFIDData.addAll(readModel);

                    readRFID.idRFID = new ArrayList<>();

                }
            }, 0, 1000);


            for (BookModel model : bookModel) {
                BookData.addAll(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //book ID - RFID ID
        TableColumn bookID = new TableColumn("Book ID");
        bookID.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookID"));

        TableColumn bookTitle = new TableColumn("Book Title");
        bookTitle.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookTitle"));

        TableColumn borrowID = new TableColumn("Borrow ID");


        TableColumn bookAuthor = new TableColumn("Book Author");
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookAuthor"));

        //beginDate - Ngay muon sach
        TableColumn beginDate = new TableColumn("Begin Date");
        beginDate.setCellValueFactory(new PropertyValueFactory<BorrowModel, Timestamp>("borrow_beginDate"));

        //returnDate - Ngay tra sach thuc te
        TableColumn returnDate = new TableColumn("Return Date");
        returnDate.setCellValueFactory(new PropertyValueFactory<BorrowModel, Timestamp>("borrow_returnDate"));

        //endDate - Ngay dang ky tra sach
        TableColumn endDate = new TableColumn("EndDate");
        endDate.setCellValueFactory(new PropertyValueFactory<BorrowModel, Timestamp>("borrow_endDate"));

        //borrow Status - 0: registered, 1: received, 2: returned
        TableColumn bookStatus = new TableColumn("Book Status");
        bookStatus.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("BookStatus"));

        tbl_rfid.setItems(RFIDData);

        tbl_rfid.getColumns().addAll(bookID, bookTitle, bookAuthor, bookStatus, beginDate, returnDate, endDate);

        tbl_book.setItems(BookData);
        tbl_book.getColumns().addAll(bookID, bookTitle, bookAuthor, bookStatus);

        //handle double click on row
        tbl_book.setRowFactory(tv -> {
            TableRow<BookModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    BookModel rowData = row.getItem();
                    System.out.println("Double click on: " + rowData.getBookID());
                    //Open new Stage of Book Log
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BookLog.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("BookLog!");
                        BookLogController mainCtl = fxmlLoader.getController();
                        //Pass whatever data you want. You can have multiple method calls here
                        mainCtl.transferMessage(rowData.getBookID());
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }
}
