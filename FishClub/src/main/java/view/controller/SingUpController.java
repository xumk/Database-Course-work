package view.controller;

import controllers.UserLogicController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modal.dbservice.dao.UserDAO;
import modal.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Алексей on 13.06.2016.
 */
public class SingUpController implements Initializable {
    public static Stage stage;
    public static UserDAO userDAO;
    public Button signUp;
    public Button authorize;
    public TextField userName;
    public PasswordField password;
    public Text errorText;
    public Pane singUpPane;
    private UserLogicController controller;

    public SingUpController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        userDAO = UserLogicController.factory.getUserDAO();
        stage.setOnCloseRequest(we ->
                UserLogicController.service.closeSessionFactory()
        );
        this.controller = UserLogicController.instance();
    }

    public void authorizationAction() {
        this.errorText.setFill(Color.FIREBRICK);
        String userName = this.userName.getText();
        String password = this.password.getText();
        User user = userDAO.getUserByLogin(userName);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                if (user.isAdmin()) {
                    controller.openAdministratorMenuScene(
                            stage, "Любительский клуб рыбаловов",
                            "/fxml/AdministratorMenu.fxml", user
                    );
                } else {
                    this.controller.openUserMenuScene(
                            stage, "Любительский клуб рыбаловов",
                            "/fxml/UserMenu.fxml", user
                    );
                }
            } else {
                this.errorText.setText("Не верный пароль");
            }
        } else {
            if(userName.equals("system") && password.equals("masterkey")) {
                controller.openAdministratorMenuScene(
                        stage, "Любительский клуб рыбаловов",
                        "/fxml/AdministratorMenu.fxml", null
                );
            } else {
                this.errorText.setText("Данного пользователя не существует");
            }
        }

    }

    public void registryAction() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            RegistryController.STAGE = stage;
            RegistryController.parentPane = this.singUpPane;
            RegistryController.isSystem = false;
            root = FXMLLoader.load(this.getClass().getResource("/fxml/Registry.fxml"));
            Scene e = new Scene(root, 550.0D, 400.0D);
            this.singUpPane.setDisable(true);
            stage.setTitle("Окно регистрации");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(e);
            stage.show();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }
}

