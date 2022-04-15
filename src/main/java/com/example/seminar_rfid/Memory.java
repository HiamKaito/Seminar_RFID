package com.example.seminar_rfid;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    public static List<String> listIdBook = new ArrayList<>();

    public static void addToListIdBook(String idBook) {
        boolean flag = true;
        for (String s : listIdBook) {
            if (s.equals(idBook)) {
                flag = false;
            }
        }

        if (flag) {
            listIdBook.add(idBook);
        }
    }
}
