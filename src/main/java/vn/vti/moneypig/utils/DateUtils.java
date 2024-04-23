package vn.vti.moneypig.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static long getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        return Long.parseLong(formattedDate);
    }

//    public static long getCurrentDateYYYYMMDDHHmmss(){
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        String formattedDate = currentDate.format(formatter);
//        return Long.parseLong(formattedDate);
//    }

    public static long getCurrentDateYYYYMMDDHHmmss() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = currentDateTime.format(formatter);
        return Long.parseLong(formattedDateTime);
    }

    public static String getCurrentTimeUTC() {
        ZonedDateTime utcTime = ZonedDateTime.now(ZoneOffset.UTC);
        return utcTime.toString();
    }
}
