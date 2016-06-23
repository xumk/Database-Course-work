package view.controller;

import controllers.ConvertionHelper;
import controllers.UserLogicController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modal.dbservice.dao.FisherDAO;
import modal.dbservice.dao.UserDAO;
import modal.entity.Fisher;
import modal.entity.User;
import modal.entity.agregation.Gender;
import view.AlertMessage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Алексей on 13.06.2016.
 */
public class RegistryController implements Initializable {
    public static Stage STAGE;
    public static Pane parentPane;
    public static boolean isSystem;

    public Button reset;
    public Button signUp;
    public TextField userName;
    public PasswordField password;
    public TextField firstName;
    public TextField lastName;
    public TextField middleName;
    public DatePicker birthDate;
    public Button back;
    public ComboBox gender;

    private ObservableList<String> genders = FXCollections.observableArrayList("Мужской", "Женский");

    private static UserDAO userDAO;
    private static FisherDAO fisherDAO;

    public RegistryController() {
        userDAO = UserLogicController.factory.getUserDAO();
        fisherDAO = UserLogicController.factory.getFisherDAO();
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.gender.setValue("Мужской");
        this.gender.setItems(this.genders);
    }

    public void backAction() {
        STAGE.close();
        parentPane.setDisable(false);
    }

    public void resetAction() {
        this.userName.clear();
        this.firstName.clear();
        this.lastName.clear();
        this.middleName.clear();
        this.password.clear();
        this.birthDate.setValue(null);
    }

    public void singUpAction() {
        String login = userName.getText();
        User user = userDAO.getUserByLogin(login);
        if (user == null) {
            Fisher fisher = new Fisher();
            fisher.setName(firstName.getText());
            fisher.setMiddleName(middleName.getText());
            fisher.setLastName(lastName.getText());
            LocalDate birthDate = this.birthDate.getValue();
            fisher.setBirthDay(ConvertionHelper.convertLocalDateToDate(birthDate));
            if (gender.getValue().equals("Мужской")) {
                fisher.setGender(Gender.MAN);
            } else {
                fisher.setGender(Gender.WOMAN);
            }
            user = new User();
            user.setPassword(this.password.getText());
            user.setLogin(userName.getText());
            user.setAdmin(isSystem);
            fisherDAO.addFisher(fisher);
            user.setFisher(fisher);
            userDAO.addUser(user);
            backAction();
        } else {
            new AlertMessage(
                    "Информационное окно",
                    "Ошибка регистрации",
                    "Данный пользователь уже существует",
                    Alert.AlertType.INFORMATION
            );
        }
    }
}

