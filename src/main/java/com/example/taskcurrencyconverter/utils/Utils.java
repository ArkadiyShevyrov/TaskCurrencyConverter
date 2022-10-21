package com.example.taskcurrencyconverter.utils;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Utils {

    public static LocalDate stringToLocalDate(String dateStr) {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        }

        LocalDate date;

        try {
            String[] splitStr = dateStr.split("-");
            if (splitStr.length != 3) {
                return null;
            }
            int year = Integer.parseInt(splitStr[0]);
            int mount = Integer.parseInt(splitStr[1]);
            int day = Integer.parseInt(splitStr[2]);

            date = LocalDate.of(year, mount, day);
        } catch (NumberFormatException | DateTimeException e) {
            return null;
        }
        return date;
    }
}
