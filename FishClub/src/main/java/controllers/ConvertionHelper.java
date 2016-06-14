package controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Алексей on 14.06.2016.
 */
public class ConvertionHelper {

    public static Date convertLocalDateToDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();
        Date res = Date.from(instant);
        return res;
    }

    public static LocalDate convertDataToLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return res;
    }
}
