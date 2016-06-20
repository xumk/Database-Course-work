import controllers.UserLogicController;
import javafx.application.Application;
import javafx.stage.Stage;
import modal.dbservice.DAOFactory;
import modal.dbservice.DBService;

import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Created by Алексей on 13.06.2016.
 */
public class RunApplication extends Application {
    private UserLogicController controller;

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    RunApplication.class.getResourceAsStream("properties/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        DBService service = DBService.instance();

        UserLogicController.service = service;
        UserLogicController.factory = DAOFactory.getInstance(service.getSessionFactory());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        controller = UserLogicController.instance();
        controller.openSingUpMenuScene(null, primaryStage,
                "Окно авторизации", "/fxml/SingUp.fxml");
    }
}
