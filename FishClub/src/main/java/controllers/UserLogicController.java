package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modal.dbservice.DAOFactory;
import modal.dbservice.DBService;
import modal.entity.User;
import view.controller.UserMenuController;

import java.io.IOException;

public class UserLogicController {
    public static DBService service;
    public static DAOFactory factory;

    public UserLogicController() {
    }

    public void openUserMenuScene(Stage parentSatage, String title, String url, User user) {
        try {
            Stage e = new Stage();
            UserMenuController.STAGE = e;
            UserMenuController.user = user;
            Parent root = FXMLLoader.load(this.getClass().getResource(url));
            Scene scene = new Scene(root, 700.0D, 400.0D);
            e.setTitle(title);
            e.setScene(scene);
            e.show();
            parentSatage.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
}
