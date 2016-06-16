import controllers.UserLogicController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modal.dbservice.DAOFactory;
import modal.dbservice.DBService;
import view.controller.SingUpController;

import java.io.IOException;

/**
 * Created by Алексей on 13.06.2016.
 */
public class RunApplication extends Application {
    public RunApplication() {
    }

    public static void main(String[] args) {
        DBService service = DBService.instance();
        UserLogicController.service = service;
        UserLogicController.factory = DAOFactory.getInstance(service.getSessionFactory());
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            SingUpController.stage = primaryStage;
            Parent e = FXMLLoader.load(this.getClass().getResource("/fxml/SingUp.fxml"));
            Scene scene = new Scene(e, 550.0D, 400.0D);
            primaryStage.setTitle("Окно авторизации");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }
}
