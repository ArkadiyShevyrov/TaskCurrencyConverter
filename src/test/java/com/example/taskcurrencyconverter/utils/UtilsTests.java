package com.example.taskcurrencyconverter.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UtilsTests {

    @Test
    void currentWork() {
        Map<LocalDate, LocalDate> testMap = new HashMap<>();
        testMap.put(LocalDate.of(2022,10,3),
                Utils.stringToLocalDate("2022-10-3"));
        testMap.put(LocalDate.of(1970,1,1),
                Utils.stringToLocalDate("1970-1-1"));
        testMap.put(LocalDate.of(2024,2,29),
                Utils.stringToLocalDate("2024-2-29"));
        testMap.put(LocalDate.of(2023,8,14),
                Utils.stringToLocalDate("2023-08-14"));
        testMap.put(LocalDate.of(2023,2,2),
                Utils.stringToLocalDate("2023-2-2"));
        testMap.put(LocalDate.of(20222,10,3),
                Utils.stringToLocalDate("20222-10-3"));


        for (LocalDate expected : testMap.keySet()) {
            LocalDate actual = testMap.get(expected);
            Assertions.assertEquals(expected, actual);
        }

    }

    @Test
    void goingBeyondLimit() {

        ArrayList<LocalDate> arrayList = new ArrayList<>();
        arrayList.add(Utils.stringToLocalDate("2022-13-4"));
        arrayList.add(Utils.stringToLocalDate("2022-12-32"));
        arrayList.add(Utils.stringToLocalDate("2022-09-31"));
        arrayList.add(Utils.stringToLocalDate("2022222-09-31"));
        arrayList.add(Utils.stringToLocalDate("0-09-31"));
        arrayList.add(Utils.stringToLocalDate("1969-09-31"));
        arrayList.add(Utils.stringToLocalDate("2023-2-29"));

        for (LocalDate actual : arrayList) {
            Assertions.assertNull(actual);
        }

    }

    @Test
    void notCorrectSplit() {

        ArrayList<LocalDate> arrayList = new ArrayList<>();
        arrayList.add(Utils.stringToLocalDate("2022:12-4"));
        arrayList.add(Utils.stringToLocalDate("2022-12:30"));
        arrayList.add(Utils.stringToLocalDate("2022:09:30"));
        arrayList.add(Utils.stringToLocalDate("2222;09!18"));
        arrayList.add(Utils.stringToLocalDate("1996-09_28"));
        arrayList.add(Utils.stringToLocalDate("1970+04-13"));
        arrayList.add(Utils.stringToLocalDate("2024-3}16"));

        for (LocalDate actual : arrayList) {
            Assertions.assertNull(actual);
        }

    }

    @Test
    void notCorrectNumber() {

        ArrayList<LocalDate> arrayList = new ArrayList<>();
        arrayList.add(Utils.stringToLocalDate("2022-1!2-4"));
        arrayList.add(Utils.stringToLocalDate("2@022-12-30"));
        arrayList.add(Utils.stringToLocalDate("2022-09-3(0"));
        arrayList.add(Utils.stringToLocalDate("2222-0^9-18"));
        arrayList.add(Utils.stringToLocalDate("1996-09-2#8"));
        arrayList.add(Utils.stringToLocalDate("19%70-04-13"));
        arrayList.add(Utils.stringToLocalDate("2024-03-1&6"));

        for (LocalDate actual : arrayList) {
            Assertions.assertNull(actual);
        }

    }
}
