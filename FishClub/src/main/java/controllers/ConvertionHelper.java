package controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Класс для конвертации одних данных в другии,
 * создан для уменьшения дублирования в коде
 */
public class ConvertionHelper {

    /**
     * Метод для преобразования тип LocalDate в Date
     *
     * @param localDate приминает дату
     * @return возвращает тоже значение с типом Date
     */
    public static Date convertLocalDateToDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    /**
     * Метод для преобразования типа Date в тип LocalDate
     *
     * @param date примимает дату
     * @return возвращает LocalDate
     */
    public static LocalDate convertDataToLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }
}
