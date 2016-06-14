package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modal.dbservice.DAOFactory;
import modal.dbservice.DBService;
import modal.entity.User;
import view.controller.AdministratorMenuController;
import view.controller.UserMenuController;

import java.io.IOException;

public class UserLogicController {
    public static DBService service;
    public static DAOFactory factory;

    public UserLogicController() {
    }

    public void openUserMenuScene(Stage parentSatage, String title, String url, User user) {
        Stage stage = new Stage();
        UserMenuController.STAGE = stage;
        UserMenuController.user = user;
        openScene(parentSatage, stage, title, url);
    }

    public void openAdministratorMenuScene(Stage parentStage, String title, String url, User user) {
        Stage stage = new Stage();
        AdministratorMenuController.stage = stage;
        AdministratorMenuController.user = user;
        openScene(parentStage, stage, title, url);
    }

    private void openScene(Stage closeStage, Stage newStage, String title, String url) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource(url));
            Scene scene = new Scene(root, 700.0D, 400.0D);
            newStage.setTitle(title);
            newStage.setScene(scene);
            newStage.show();
            closeStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
