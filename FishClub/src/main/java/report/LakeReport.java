package report;

import javafx.collections.ObservableList;
import modal.entity.Lake;
import modal.entity.joinentity.Distance;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Алексей on 23.06.2016.
 */
public class LakeReport {
    private static final String FILE_NAME = "Lake Report.xls";
    private static final String FILE_PATH = "./download/";

    @SuppressWarnings("deprecation")
    public static void writeIntoExcel(ObservableList<Distance> data) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Озера");

        Font font= book.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        CellStyle style = book.createCellStyle();
        style.setFont(font);

        // Нумерация начинается с нуля
        int i = 0;
        Row row = sheet.createRow(i);

        Cell name = row.createCell(0);
        name.setCellValue("Название озера");
        name.setCellStyle(style);
        Cell depthLake = row.createCell(1);
        depthLake.setCellValue("Глубина озера");
        depthLake.setCellStyle(style);
        Cell areaLake = row.createCell(2);
        areaLake.setCellValue("Площадь озера");
        areaLake.setCellStyle(style);
        Cell distance = row.createCell(3);
        distance.setCellValue("Расстояние");
        distance.setCellStyle(style);
        ++i;

        for (Distance dist : data) {
            row = sheet.createRow(i);
            Lake lake = dist.getLake();
            name = row.createCell(0);
            name.setCellValue(lake.getName());
            depthLake = row.createCell(1);
            depthLake.setCellValue(lake.getDepth());
            areaLake = row.createCell(2);
            areaLake.setCellValue(lake.getArea());
            distance = row.createCell(3);
            distance.setCellValue(dist.getDistance());
            ++i;
        }

        Distance min = data.stream()
                .min((p1, p2) ->
                        Double.compare(p1.getDistance(), p2.getDistance()))
                .get();
        row = sheet.createRow(i);

        Cell  all = row.createCell(1);
        all.setCellStyle(style);
        all.setCellValue("Расстояние до ближайщего озера");
        Cell  nameL = row.createCell(2);
        nameL.setCellValue(min.getLake().getName());
        nameL.setCellStyle(style);
        Cell  allValue = row.createCell(3);
        allValue.setCellValue(min.getDistance());
        allValue.setCellStyle(style);

        // Меняем размер столбца
        for (int j = 0; j < 4; j++) {
            sheet.autoSizeColumn(j);
        }

        // Записываем всё в файл
        File file = new File(FILE_PATH);
        file.mkdirs();
        try(FileOutputStream fio = new FileOutputStream(FILE_PATH + FILE_NAME)) {
            book.write(fio);
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
