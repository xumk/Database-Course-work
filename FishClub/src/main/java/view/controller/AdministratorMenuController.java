package view.controller;

import controllers.ConvertionHelper;
import controllers.UserLogicController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modal.dbservice.dao.*;
import modal.entity.*;
import modal.entity.agregation.Gender;
import view.AlertMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

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
    public TableView<Lure> tableBait;
    public TableColumn<Lure, String> baitName;
    public TableColumn<Lure, Integer> baitCountHooks;
    public TableColumn<Lure, Double> bairWeight;
    public TableColumn<Lure, Double> baitDepth;
    public TableColumn<Lure, Boolean> isLive;
    public TableView<Fish> tableFish;
    public TableColumn<Fish, String> fishName;
    public TableColumn<Fish, String> fishFamily;
    public TableColumn fishWeight;
    public TableColumn<Fish, Double> fishMinWeight;
    public TableColumn<Fish, Double> fishMaxWeight;
    public TableColumn<Fish, Double> fishDepth;
    public TableView<Lake> lakeTable;
    public TableColumn<Lake, String> lakeName;
    public TableColumn<Lake, Double> lakeDepth;
    public TableColumn<Lake, Double> lakeArea;
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

    private ObservableList<String> genders = observableArrayList("Мужской", "Женский");
    private UserDAO userDAO;
    private FisherDAO fisherDAO;
    private LakeDAO lakeDAO;
    private LureDAO lureDAO;
    private FishDAO fishDAO;
    private ObservableList<Lake> lakeData;
    private ObservableList<Lure> lureData;
    private ObservableList<Fish> fishData;

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
            Gender gender = genderComboBox.getValue().equals("Мужской") ? Gender.MAN : Gender.WOMAN;
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

                fisherDAO.updateFisher(fisher);
                user.setFisherman(fisher);
                userDAO.updateUser(user);

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
        lakeDAO = UserLogicController.factory.getLakeDAO();
        lureDAO = UserLogicController.factory.getLureDAO();
        fishDAO = UserLogicController.factory.getFishDAO();
        this.genderComboBox.setItems(this.genders);

        initializeTableLake();
        initializeTableLure();
        initializeTableFish();
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

    private void initializeTableFish() {
        fishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        fishFamily.setCellValueFactory(new PropertyValueFactory<>("family"));
        fishMinWeight.setCellValueFactory(new PropertyValueFactory<>("minWeight"));
        fishMaxWeight.setCellValueFactory(new PropertyValueFactory<>("maxWeight"));
        fishDepth.setCellValueFactory(new PropertyValueFactory<>("depthLiving"));
        fishData = observableArrayList(fishDAO.getAllFishs());
        tableFish.setItems(fishData);
    }

    private void initializeTableLure() {
        baitName.setCellValueFactory(new PropertyValueFactory<>("name"));
        baitCountHooks.setCellValueFactory(new PropertyValueFactory<>("countHooks"));
        bairWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        baitDepth.setCellValueFactory(new PropertyValueFactory<>("divingDepth"));
        isLive.setCellValueFactory(
                param -> new SimpleBooleanProperty(param.getValue().isImitation())
        );
        isLive.setCellFactory( CheckBoxTableCell.forTableColumn(isLive));
        lureData = observableArrayList(lureDAO.getAllLures());
        tableBait.setItems(lureData);
    }

    private void initializeTableLake() {
        lakeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        lakeDepth.setCellValueFactory(new PropertyValueFactory<>("depth"));
        lakeArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        lakeData = observableArrayList(lakeDAO.getAllLake());
        lakeTable.setItems(lakeData);
    }
}
