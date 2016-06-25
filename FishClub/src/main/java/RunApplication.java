import controllers.UserLogicController;
import javafx.application.Application;
import javafx.stage.Stage;
import modal.dbservice.DAOFactory;
import modal.dbservice.DBService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Основной класс программы унаследованный от Application
 * для возможности запуска JavaFX окон
 */
public class RunApplication extends Application {
    private UserLogicController controller;
    private static Logger logger = Logger.getLogger(RunApplication.class.getName());

    /**
     * Функция запуска программы
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            // инициализируем логгер
            LogManager.getLogManager().readConfiguration(
                    RunApplication.class.getResourceAsStream("properties/logging.properties"));
        } catch (IOException e) {
            // пишем в лог ошибку
            logger.log(Level.SEVERE, "Невозможно загрузить файл конфигурации логерра: " + e.toString());
        }
        DBService service = DBService.instance();

        UserLogicController.service = service;
        /* создаем класс-фактори, который потом будем использовать длz
                создания всех DAO (классы для работы с базой данной)
             */
        UserLogicController.factory = DAOFactory.getInstance(service.getSessionFactory());
        launch(args);
    }

    /**
     * Класс для стратра приложения JavaFX
     *
     * @param primaryStage главная сцена приложения
     */
    @Override
    public void start(Stage primaryStage) {
        controller = UserLogicController.instance();
        controller.openSingUpMenuScene(null, primaryStage,
                "Окно авторизации", "/fxml/SingUp.fxml");
    }
}
