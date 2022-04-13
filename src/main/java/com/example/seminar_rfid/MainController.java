package com.example.seminar_rfid;

import com.example.seminar_rfid.BUS.BookBUS;
import com.example.seminar_rfid.BUS.ReadBUS;
import com.example.seminar_rfid.model.BookModel;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements Initializable {
    @FXML
    protected TableView tbl_book;
    private final ObservableList<BookModel> BookData =
            FXCollections.observableArrayList();

    ArrayList<BookModel> model = new ArrayList<>();
    BookBUS bookBus;
    ReadBUS readBUS;

    Read readRFID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbl_book.setEditable(true);
        try {
            bookBus = new BookBUS();
            readBUS = new ReadBUS();
//            model = bookBus.getBookInfByID();


            Timer countDown = new Timer();
            countDown.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    BookData.clear();
                    model.clear();


                    readRFID.searchItem();
//                    displayInfor();
                    for (String s : readRFID.idRFID) {
                        System.out.println(s);
                        model.add(
                                bookBus.getBookInfor(readBUS.getBookID(s))
                        );
                    }
                    BookData.addAll(model);

                    readRFID.idRFID = new ArrayList<>();

                }
            }, 0, 1000);





//            model.add(
//                    bookBus.getBookInfor(readBUS.getBookID("E28011606000020958CD98FE"))
//            );



//            for (BookModel model : model) {
//                System.out.println(model.getBookID());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //book ID means RFID ID
        TableColumn bookID = new TableColumn("Book ID");
        bookID.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookID"));

        TableColumn bookTitle = new TableColumn("Book Title");
        bookTitle.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookTitle"));

        TableColumn borrowID = new TableColumn("Borrow ID");
        TableColumn bookAuthor = new TableColumn("Book Author");
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookModel, String>("BookAuthor"));

        //beginDate - Ngay muon sach
        TableColumn beginDate = new TableColumn("Begin Date");
        //returnDate - Ngay tra sach thuc te
        TableColumn returnDate = new TableColumn("Return Date");
        //endDate - Ngay dang ky tra sach
        TableColumn endDate = new TableColumn("EndDate");
        //borrow Status - 0: registered, 1: received, 2: returned
        TableColumn bookStatus = new TableColumn("Book Status");
        bookStatus.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("BookStatus"));

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
