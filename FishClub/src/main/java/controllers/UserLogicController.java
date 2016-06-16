package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modal.dbservice.DAOFactory;
import modal.dbservice.DBService;
import modal.entity.Fish;
import modal.entity.Lake;
import modal.entity.Lure;
import modal.entity.User;
import view.controller.AdministratorMenuController;
import view.controller.UserMenuController;
import view.controller.editcontroller.FishEditController;
import view.controller.editcontroller.LakeEditController;
import view.controller.editcontroller.LureEditController;

import java.io.IOException;

public class UserLogicController {
    public static DBService service;
    public static DAOFactory factory;

    private static UserLogicController instance;

    private UserLogicController() {
    }

    public synchronized static UserLogicController instance() {
        if (instance == null) {
            instance = new UserLogicController();
        }

        return instance;
    }


    public void openUserMenuScene(Stage parentSatage, String title, String url, User user) {
        Stage stage = new Stage();
        UserMenuController.stage = stage;
        UserMenuController.user = user;
        openScene(parentSatage, stage, title, url, 700, 400);
    }

    public void openAdministratorMenuScene(Stage parentStage, String title, String url, User user) {
        Stage stage = new Stage();
        AdministratorMenuController.stage = stage;
        AdministratorMenuController.user = user;
        openScene(parentStage, stage, title, url, 700, 400);
    }

    public void openAddAndEditFishTable(Fish fish, Pane parentPane, String title, String url) {
        Stage stage = new Stage();
        FishEditController.stage = stage;
        FishEditController.parentPane = parentPane;
        FishEditController.fish = fish;
        openScene(null, stage, title, url, 216, 349);
        parentPane.setDisable(true);
    }

    public void openAddAndEditLakeTable(Lake lake, Pane parentPane, String title, String url) {
        Stage stage = new Stage();
        LakeEditController.stage = stage;
        LakeEditController.parentPane = parentPane;
        LakeEditController.lake = lake;
        openScene(null, stage, title, url, 229, 259);
        parentPane.setDisable(true);
    }

    public void openAddAndEditLureTable(Lure lure, Pane parentPane, String title, String url) {
        Stage stage = new Stage();
        LureEditController.stage = stage;
        LureEditController.parentPane = parentPane;
        LureEditController.lure = lure;
        openScene(null, stage, title, url, 271, 397);
        parentPane.setDisable(true);
    }

    private void openScene(
            Stage closeStage, Stage newStage, String title, String url,
            double width, double height) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource(url));
            Scene scene = new Scene(root, width, height);
            newStage.setTitle(title);
            newStage.setScene(scene);
            newStage.show();
            if (closeStage != null) {
                closeStage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
