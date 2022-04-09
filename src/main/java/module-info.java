module com.example.seminar_rfid {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires CAENRFIDLibrary;

    opens com.example.seminar_rfid to javafx.fxml;
    exports com.example.seminar_rfid;
}