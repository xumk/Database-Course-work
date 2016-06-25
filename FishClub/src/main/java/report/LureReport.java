package report;

import javafx.collections.ObservableList;
import modal.entity.Lure;
import modal.entity.joinentity.Availability;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Класс для создания xsl отчета о наживках
 */
public class LureReport {
    private static final String FILE_PATH = "./download/";
    private static final String FILE_NAME = "Lure Report.xls";
    private static Logger logger = Logger.getLogger(LureReport.class.getName());

    /**
     * Записать данные в xsl файл
     *
     * @param data данные отчета
     */
    @SuppressWarnings("deprecation")
    public static void writeIntoExcel(ObservableList<Availability> data) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Наживки");

        Font font = book.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        // создать стиль ячейки
        CellStyle style = book.createCellStyle();
        style.setFont(font);

        // Нумерация начинается с нуля
        int i = 0;
        Row row = sheet.createRow(i);

        Cell name = row.createCell(0);
        name.setCellValue("Название наживки");
        name.setCellStyle(style);
        Cell countHook = row.createCell(1);
        // задать значений ячейки
        countHook.setCellValue("Количество крючков");
        countHook.setCellStyle(style);
        Cell weight = row.createCell(2);
        weight.setCellValue("Вес наживки");
        weight.setCellStyle(style);
        Cell depthDown = row.createCell(3);
        // задать значение ячейки
        depthDown.setCellValue("Глубина погружения");
        depthDown.setCellStyle(style);
        Cell isLive = row.createCell(4);
        isLive.setCellValue("Тип наживки");
        isLive.setCellStyle(style);
        Cell count = row.createCell(5);
        count.setCellValue("Количество");
        count.setCellStyle(style);

        ++i;
        // пройти по всем данным и созать строки с ними в отчете
        for (Availability avail : data) {
            row = sheet.createRow(i);
            Lure lure = avail.getLure();
            name = row.createCell(0);
            name.setCellValue(lure.getName());
            // создание новой ячейки
            countHook = row.createCell(1);
            countHook.setCellValue(lure.getCountHooks());
            weight = row.createCell(2);
            weight.setCellValue(lure.getWeight());
            depthDown = row.createCell(3);
            // задание значений ячейки
            depthDown.setCellValue(lure.getDivingDepth());
            isLive = row.createCell(4);
            isLive.setCellValue(lure.isImitation() ? "Искусственная" : "Живая");
            count = row.createCell(5);
            count.setCellValue(avail.getCountLure());
            ++i;
        }

        // получение суммы количество всех наживок
        double sumLure = data.stream()
                .map(Availability::getCountLure)
                .mapToDouble(s -> s)
                .sum();
        row = sheet.createRow(i);

        Cell all = row.createCell(4);
        all.setCellStyle(style);
        // вывод результатов
        all.setCellValue("Всего наживки");
        Cell allValue = row.createCell(5);
        allValue.setCellValue(sumLure);
        allValue.setCellStyle(style);

        // Меняем размер столбца
        for (int j = 0; j < 6; j++) {
            sheet.autoSizeColumn(j);
        }

        // Записываем всё в файл
        File file = new File(FILE_PATH);
        file.mkdirs();
        try (FileOutputStream fio = new FileOutputStream(FILE_PATH + FILE_NAME)) {
            book.write(fio);
            book.close();
        } catch (IOException e) {
            logger.severe(Arrays.toString(e.getStackTrace()));
        }
    }
}
