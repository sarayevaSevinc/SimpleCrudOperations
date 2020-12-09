package com.ibar.demo.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static LocalDate convertStringToLocalDate(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = "16/08/2016";

        //convert String to LocalDate
       return LocalDate.parse(date, formatter);
    }
}
