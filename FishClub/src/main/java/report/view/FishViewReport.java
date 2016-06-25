package report.view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import modal.entity.Fish;

import java.util.List;

/**
 * Класс для создания визуального отчета о рыбах
 */
public class FishViewReport {

    /**
     * Метод создания отчета
     *
     * @param stage сцена, на которой нужно создать отчет
     * @param data  данные по которы нужно создать отчет
     */
    public static void createReport(Stage stage, List<Fish> data) {
        stage.setTitle("Вес рыб");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("Вес рыб");
        //задаем название осям
        xAxis.setLabel("Рыба");
        yAxis.setLabel("Вес");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Максимальный вес");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Минимальный вес");
        for (Fish fish : data) {
            // добавляем данные в серии
            series1.getData().add(
                    new XYChart.Data<>(fish.getName(), fish.getMaxWeight())
            );
            series2.getData().add(
                    new XYChart.Data<>(fish.getName(), fish.getMinWeight())
            );
        }

        Scene scene = new Scene(bc, 400, 400);
        bc.getData().addAll(series1, series2);
        stage.setScene(scene);
        // показываем сцену
        stage.show();
    }
}
