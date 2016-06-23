package report.view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import modal.entity.Lake;
import modal.entity.joinentity.Lived;

/**
 * Created by Алексей on 23.06.2016.
 */
public class LakeAndFishViewReport {
    public static void createReport(Stage stage, Lake data) {
        stage.setTitle("Количество рыб в озере");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("Количество рыб");
        xAxis.setLabel("Рыбы");
        yAxis.setLabel("Популяция");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Популяция рыбы");
        for (Lived lived : data.getFishs()) {
            series1.getData().add(
                    new XYChart.Data<>(lived.getFish().getName(), lived.getCountFish())
            );
        }

        Scene scene = new Scene(bc, 400, 400);
        bc.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }
}
