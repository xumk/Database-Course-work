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
 * Графический отчет о рыбах и их популяции в конкретном озере
 */
public class LakeAndFishViewReport {

    /**
     * Метод создания отчет
     *
     * @param stage сцена, на которой нужно показать отчет
     * @param data  данные для отчет
     */
    public static void createReport(Stage stage, Lake data) {
        stage.setTitle("Количество рыб в озере");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("Количество рыб");
        // задаем название осей
        xAxis.setLabel("Рыбы");
        yAxis.setLabel("Популяция");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Популяция рыбы");
        for (Lived lived : data.getFishs()) {
            // наполняем серию данных
            series1.getData().add(
                    new XYChart.Data<>(lived.getFish().getName(), lived.getCountFish())
            );
        }

        Scene scene = new Scene(bc, 400, 400);
        bc.getData().add(series1);
        stage.setScene(scene);
        // показываем отчет
        stage.show();
    }
}
