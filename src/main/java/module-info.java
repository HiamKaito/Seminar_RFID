module com.example.seminar_rfid {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires CAENRFIDLibrary;
    requires java.sql;

    opens com.example.seminar_rfid to javafx.fxml;
    exports com.example.seminar_rfid;
    exports com.example.seminar_rfid.DAO;
    opens com.example.seminar_rfid.DAO to javafx.fxml;
    exports com.example.seminar_rfid.model;
    opens com.example.seminar_rfid.model to javafx.fxml;
}