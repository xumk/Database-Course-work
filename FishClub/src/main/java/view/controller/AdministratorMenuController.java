package view.controller;

import controllers.ConvertionHelper;
import controllers.UserLogicController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modal.dbservice.dao.FisherDAO;
import modal.dbservice.dao.UserDAO;
import modal.entity.Fisher;
import modal.entity.User;
import modal.entity.agregation.Gender;
import view.AlertMessage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Алексей on 14.06.2016.
 */
public class AdministratorMenuController implements Initializable {
    public static Stage stage;
    public static User user;

    public TextField userName;
    public PasswordField password;
    public TextField firstName;
    public TextField lastName;
    public TextField middleName;
    public DatePicker birthDate;
    public Button editButton;
    public ComboBox genderComboBox;
    public Button saveButton;
    public TableView tableBait;
    public TableColumn baitName;
    public TableColumn baitCountHooks;
    public TableColumn bairWeight;
    public TableColumn baitDepth;
    public TableColumn isLive;
    public TableView tableFish;
    public TableColumn fishName;
    public TableColumn fsihFamily;
    public TableColumn fishWeight;
    public TableColumn fishMinWeight;
    public TableColumn fishMaxWeight;
    public TableColumn fishDepth;
    public TableView lakeTable;
    public TableColumn lakeName;
    public TableColumn lakeDepth;
    public Button exit;
    public Button logOut;
    public Button editLureButton;
    public Button deleteLureButton;
    public Button addLureButton;
    public Button addFishButton;
    public Button deleteFishButton;
    public Button editFishButton;
    public Button addLakeButton;
    public Button deleteLakeButton2;
    public Button editLakeButton;

    private ObservableList<String> genders = FXCollections.observableArrayList("Мужской", "Женский");
    private UserDAO userDAO;
    private FisherDAO fisherDAO;

    private void fillInformationOnUserData(User user) {
        Fisher fisher = user.getFisherman();
        this.lastName.setText(fisher.getLastName());
        this.userName.setText(user.getLogin());
        this.firstName.setText(fisher.getName());
        this.birthDate.setValue(ConvertionHelper.convertDataToLocalDate(fisher.getBirthDay()));
        this.middleName.setText(fisher.getMiddleName());
        this.password.setText(user.getPassword());
        if (fisher.getGender() == Gender.MAN) {
            genderComboBox.setValue("Мужской");
        } else {
            genderComboBox.setValue("Женский");
        }
    }

    public void exitProgramm() {
        stage.close();
    }

    public void editAction() {
        this.lastName.setEditable(true);
        this.userName.setEditable(true);
        this.firstName.setEditable(true);
        this.birthDate.setDisable(false);
        this.middleName.setEditable(true);
        this.password.setEditable(true);
        this.genderComboBox.setDisable(false);
    }

    public void saveAction() {
        if (lastName.isEditable()) {
            Fisher fisher = user.getFisherman();
            Date birthDate = ConvertionHelper.convertLocalDateToDate(this.birthDate.getValue());
            Gender gender = genderComboBox.getValue().equals("Мужской")? Gender.MAN : Gender.WOMAN;
            if (!user.getLogin().equals(userName.getText())
                    || !user.getPassword().equals(password.getText())
                    || !fisher.getName().equals(firstName.getText())
                    || !fisher.getMiddleName().equals(middleName.getText())
                    || !fisher.getLastName().equals(lastName.getText())
                    || !fisher.getBirthDay().equals(birthDate)
                    || !fisher.getGender().equals(gender)) {
                user.setLogin(userName.getText());
                user.setPassword(password.getText());
                fisher.setName(firstName.getText());
                fisher.setMiddleName(middleName.getText());
                fisher.setLastName(lastName.getText());
                fisher.setBirthDay(birthDate);
                fisher.setGender(gender);
                try {
                    fisherDAO.updateFisher(fisher);
                    user.setFisherman(fisher);
                    userDAO.updateUser(user);
                } catch (SQLException e) {
                    new AlertMessage(
                            "Ошибка",
                            "Ошибка при обновлении",
                            e.getMessage(),
                            Alert.AlertType.ERROR
                    );
                }
                this.lastName.setEditable(false);
                this.userName.setEditable(false);
                this.firstName.setEditable(false);
                this.birthDate.setDisable(true);
                this.middleName.setEditable(false);
                this.password.setEditable(false);
                this.genderComboBox.setDisable(true);
            } else {
                new AlertMessage(
                        "Системное сообщение",
                        "Измененений не обнаружено",
                        null,
                        Alert.AlertType.INFORMATION
                );
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userDAO = UserLogicController.factory.getUserDAO();
        fisherDAO = UserLogicController.factory.getFisherDAO();
        this.genderComboBox.setItems(this.genders);

        this.fillInformationOnUserData(user);
        this.logOut.setOnAction((event) -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(this.getClass().getResource("/fxml/SingUp.fxml"));
                Scene e = new Scene(root, 550.0D, 400.0D);
                Stage stage = new Stage();
                AdministratorMenuController.stage.close();
                SingUpController.STAGE = stage;
                stage.setTitle("Окно авторизации");
                stage.setScene(e);
                stage.show();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        });
    }
}
