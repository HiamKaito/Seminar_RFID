package com.example.seminar_rfid;

import com.caen.RFIDLibrary.*;

import java.util.*;

public class Read {

    public static List<String> idRFID = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        // change value display
        Timer countDown = new Timer();
        countDown.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                searchItem();
                displayInfor();
                idRFID = new ArrayList<>();

            }
        }, 0, 1000);


    }

    public static void searchItem() {
// TODO Auto-generated method stub
        CAENRFIDReader MyReader = new CAENRFIDReader();
        try {
            MyReader.Connect(CAENRFIDPort.CAENRFID_TCP, "192.168.1.2");
            CAENRFIDLogicalSource MySource = MyReader.GetSource("Source_0");

            //get Reader Infor
            CAENRFIDReaderInfo Info = MyReader.GetReaderInfo();

            String Model = Info.GetModel();
            String SerialNumber = Info.GetSerialNumber();
            String FWRelease = MyReader.GetFirmwareRelease();
            // tinh theo cong de xac dinh khoang cach
            int power = MyReader.GetPower();

            // in ra thong tin
            System.out.println("Model: " + Model);
            System.out.println("SerialNumber: " + SerialNumber);
            System.out.println("FWRelease: " + FWRelease);
            System.out.println("power: " + power);

            System.out.println("");
            //thoi gian nhan
            MySource.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S1);

            // chua thong tin cua cac tag
            // chua tat ca tong tin quet tren device
            CAENRFIDTag[] MyTags = MySource.InventoryTag();

            if (MyTags.length > 0) {
                // show list cac thong tin san pham
                //id san pham la duy nhat: exmple 48718273123
                //
                for (int i = 0; i < MyTags.length; i++) {
//                    System.out.println("EPC: " + hex(MyTags[i].GetId()) );
//                            " | Antenna : " + MyTags[i].GetAntenna() +
//                            " | TID:" + (MyTags[i].GetTID()) +
//                            " | RSSI : " + Integer.valueOf(MyTags[i].GetRSSI()));

                    idRFID.add(hex(MyTags[i].GetId()));
                }
            }

            MyReader.Disconnect();
        } catch (Exception ex) {
            System.out.println(ex);
            try {
                MyReader.Disconnect();
            } catch (CAENRFIDException e) {
                e.printStackTrace();
            }

        }
    }

    public static void displayInfor() {
        for (String s : idRFID) {
            if (s.equals("41003200300036003400300030003100")) {
                System.out.println("SP 1");
            }
            if (s.equals("300EFE2F94D01C02540BE93A")) {
                System.out.println("SP 2");
            }
            if (s.equals("E200001730010136290000DD")) {
                System.out.println("SP 3");
            }

//            System.out.println("Thong qua 1 san pham");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * @return tra ve moi chuoi duoc in hoa
     */
    public static String hex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
            // upper case
            // result.append(String.format("%02X", aByte));
        }
        return result.toString().toUpperCase();
    }


}

/*
/home/hiamkaito/.jdks/openjdk-18/bin/java -javaagent:/snap/intellij-idea-community/352/lib/idea_rt.jar=43969:/snap/intellij-idea-community/352/bin -Dfile.encoding=UTF-8 -classpath /home/hiamkaito/.m2/repository/org/openjfx/javafx-controls/18-ea+6/javafx-controls-18-ea+6.jar:/home/hiamkaito/.m2/repository/org/openjfx/javafx-graphics/18-ea+6/javafx-graphics-18-ea+6.jar:/home/hiamkaito/.m2/repository/org/openjfx/javafx-base/18-ea+6/javafx-base-18-ea+6.jar:/home/hiamkaito/.m2/repository/org/openjfx/javafx-fxml/18-ea+6/javafx-fxml-18-ea+6.jar:/home/hiamkaito/Documents/Semina chuyên đề/drive-download-20220319T033340Z-001/CAEN-RFID-SDK_4_7_0/SDK_4_7_0/JAVA/WIN32/lib/RXTXcomm.jar -p /home/hiamkaito/.m2/repository/org/openjfx/javafx-base/18-ea+6/javafx-base-18-ea+6-linux.jar:/home/hiamkaito/.m2/repository/org/openjfx/javafx-graphics/18-ea+6/javafx-graphics-18-ea+6-linux.jar:/home/hiamkaito/Documents/Semina chuyên đề/drive-download-20220319T033340Z-001/CAEN-RFID-SDK_4_7_0/SDK_4_7_0/JAVA/WIN32/CAENRFIDLibrary.jar:/home/hiamkaito/.m2/repository/org/openjfx/javafx-controls/18-ea+6/javafx-controls-18-ea+6-linux.jar:/home/hiamkaito/.m2/repository/org/openjfx/javafx-fxml/18-ea+6/javafx-fxml-18-ea+6-linux.jar:/home/hiamkaito/Documents/Java Project/target/classes:/home/hiamkaito/.m2/repository/org/controlsfx/controlsfx/11.1.0/controlsfx-11.1.0.jar:/home/hiamkaito/.m2/repository/com/dlsc/formsfx/formsfx-core/11.3.2/formsfx-core-11.3.2.jar -m com.example.seminar_rfid/com.example.seminar_rfid.Read
Model: R1290IU
SerialNumber: 0674042518190513
FWRelease: 1.1.0
power: 200

EPC: 41003200300036003400300030003100 | Antenna : Ant0 | TID:null | RSSI : 0
EPC: 300EFE2F94D01C02540BE93A | Antenna : Ant0 | TID:null | RSSI : 0
EPC: E200001730010136290000DD | Antenna : Ant0 | TID:null | RSSI : 0
EPC: 300EFE2F94D01C42540BE4F9 | Antenna : Ant0 | TID:null | RSSI : 0
EPC: 41003200300036003400300030003100 | Antenna : Ant0 | TID:null | RSSI : 0

Process finished with exit code 0

 */