package report.view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import modal.entity.Lure;

import java.util.List;

/**
 * Created by Алексей on 23.06.2016.
 */
public class LureViewReport {

    public static void createReport(Stage stage, List<Lure> data) {
        stage.setTitle("Глубоководность наживок");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("Глубоководность наживок");
        xAxis.setLabel("Наживки");
        yAxis.setLabel("Глубина");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Глубина наживки");
        for (Lure lure : data) {
            series1.getData().add(
                    new XYChart.Data<>(lure.getName(), lure.getDivingDepth())
            );
        }

        Scene scene = new Scene(bc, 400, 400);
        bc.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }
}
