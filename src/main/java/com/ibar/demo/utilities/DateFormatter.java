package com.ibar.demo.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static LocalDate convertStringToLocalDate(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
       return LocalDate.parse(s, formatter);
    }
}
