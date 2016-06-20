package view.controller;

import controllers.UserLogicController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.entity.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Алексей on 13.06.2016.
 */
public class UserMenuController implements Initializable {
    public static Stage stage;
    public static User user;
    public Button exit;
    public TableColumn lakeDistance;
    public TableColumn lakeName;
    public TableView lakeTable;
    public TableColumn fishName;
    public TableView tableFish;
    public TableColumn baitName;
    public TableView tableBait;
    public DatePicker birthDate;
    public TextField middleName;
    public TextField lastName;
    public TextField firstName;
    public PasswordField password;
    public TextField userName;
    public Button editButton;
    public Button logOut;
    public ComboBox genderComboBox;
    public Button saveButton;
    public Button addLureButton;
    public Button deleteLureButton;
    public Button editLureButton;
    public Button downloadLureButton;
    public Button downloadFishButton;
    public Button deleteFishButton;
    public Button addFishButton;
    public Button downloadLakeButton;
    public Button editLakeButton;
    public Button deleteLakeButton;
    public Button addLakeButton;
    public TableColumn baitCount;

    private ObservableList<String> genders = FXCollections.observableArrayList("Мужской", "Женский");
    private UserLogicController controller;

    public UserMenuController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        controller = UserLogicController.instance();
        this.genderComboBox.setItems(this.genders);
        stage.setOnCloseRequest(we ->
                UserLogicController.service.closeSessionFactory()
        );
        this.fillInformationOnUserData(user);
        this.logOut.setOnAction((event) -> {
            Stage stage = new Stage();
            SingUpController.stage = stage;
            controller.openSingUpMenuScene(
                    UserMenuController.stage, stage,
                    "Окно авторизации", "/fxml/SingUp.fxml"
            );
        });
    }

    private void fillInformationOnUserData(User user) {
        controller.fillInformUser(user, firstName, middleName, lastName,
                userName, password, birthDate, genderComboBox);
    }

    public void exitProgramm() {
        stage.fireEvent(new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST
        ));
    }

    public void editAction() {
        controller.edit(userName, password, firstName, middleName,
                lastName, birthDate, genderComboBox);
    }

    public void saveAction() {
        controller.updateInforUser(user, userName, password,
                firstName, middleName,
                lastName, this.birthDate, genderComboBox);
    }
}
