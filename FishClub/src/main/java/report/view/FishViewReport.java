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
 * Created by Алексей on 23.06.2016.
 */
public class FishViewReport {
    public static void createReport(Stage stage, List<Fish> data) {
        stage.setTitle("Вес рыб");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("Вес рыб");
        xAxis.setLabel("Рыба");
        yAxis.setLabel("Вес");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Максимальный вес");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Минимальный вес");
        for (Fish fish : data) {
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
        stage.show();
    }
}
