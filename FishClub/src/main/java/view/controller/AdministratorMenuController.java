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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.dbservice.dao.*;
import modal.entity.*;
import modal.entity.agregation.Gender;
import modal.helpmodal.LivedFishLake;
import view.AlertMessage;
import view.controller.editcontroller.FishEditController;
import view.controller.editcontroller.LakeEditController;
import view.controller.editcontroller.LureEditController;

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
    public Button deleteLakeButton;
    public Button editLakeButton;
    public Pane pane;
    public Pane fishLinkLurePane;
    public Button deleteFishLinkLureButton;
    public Button addFishBLinkLureutton;
    public Pane lakeLinkFishPane;
    public Button deleteLakeLinkFishButton;
    public Button addLakeLinkFishButton;
    public TableColumn<Lure, Long> idLureLink;
    public TableColumn<Lure, String> nameLureLink;
    public ToggleButton openMasterDetailFishGridButton;
    public TableView<LivedFishLake> fishLivedTable;
    public TableColumn<LivedFishLake, Long> idFishLink;
    public TableColumn<LivedFishLake, String> nameFishLink;
    public TableColumn<LivedFishLake, Integer> countFishiLake;
    public Button editLakeLinkFishButton;
    public ToggleButton openMasterDetailLakeGridButton;
    public TableView<Lure> linkTableLure;

    private ObservableList<String> genders = observableArrayList("Мужской", "Женский");
    private UserDAO userDAO;
    private FisherDAO fisherDAO;
    private LakeDAO lakeDAO;
    private LureDAO lureDAO;
    private FishDAO fishDAO;
    private ObservableList<Lake> lakeData;
    private ObservableList<Lure> lureData;
    private ObservableList<Fish> fishData;
    private ObservableList<Lure> linkLureData;
    private ObservableList<LivedFishLake> linkFishData;
    private UserLogicController controller;
    private Fish currentFish;
    private Lake currentLake;

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
        stage.fireEvent(new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST
        ));
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
        this.controller = UserLogicController.instance();

        openMasterDetailFishGridButton.setOnAction(evt -> {
            if (openMasterDetailFishGridButton.isSelected()
                    && tableFish.getSelectionModel().getSelectedItem() != null) {
                currentFish = tableFish.getSelectionModel().getSelectedItem();
                linkLureData = observableArrayList(currentFish.getLures());
                idLureLink.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameLureLink.setCellValueFactory(new PropertyValueFactory<>("name"));
                linkTableLure.setItems(linkLureData);
                fishLinkLurePane.setVisible(true);
            } else {
                fishLinkLurePane.setVisible(false);
            }
        });

        openMasterDetailLakeGridButton.setOnAction(evt -> {
            if (openMasterDetailLakeGridButton.isSelected()
                    && lakeTable.getSelectionModel().getSelectedItem() != null) {
                currentLake = lakeTable.getSelectionModel().getSelectedItem();
                linkFishData = controller.createLinkFish(currentLake.getFishs(), currentLake);
                idFishLink.setCellValueFactory(new PropertyValueFactory<>("idFish"));
                nameFishLink.setCellValueFactory(new PropertyValueFactory<>("nameFish"));
                countFishiLake.setCellValueFactory(new PropertyValueFactory<>("countFishLived"));
                fishLivedTable.setItems(linkFishData);
                lakeLinkFishPane.setVisible(true);
            } else {
                lakeLinkFishPane.setVisible(false);
            }
        });

        stage.setOnCloseRequest(we ->
                UserLogicController.service.closeSessionFactory()
        );

        initializeTableLake();
        initializeTableLure();
        initializeTableFish();
        this.fillInformationOnUserData(user);
        this.logOut.setOnAction((event) -> {
            Parent root = null;
            try {
                Stage stage = new Stage();
                SingUpController.stage = stage;
                root = FXMLLoader.load(this.getClass().getResource("/fxml/SingUp.fxml"));
                Scene e = new Scene(root, 550.0D, 400.0D);
                AdministratorMenuController.stage.close();
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
        isLive.setCellFactory(CheckBoxTableCell.forTableColumn(isLive));
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

    public void addFishAction() {
        FishEditController.data = fishData;
        controller.openAddAndEditFishTable(
                null, pane, "Окно добавления рыбы",
                "/fxml/editform/FishEditAdmin.fxml"
        );
    }

    public void deleteFishAction() {
        Fish fish = tableFish.getSelectionModel().getSelectedItem();
        if (fish != null) {
            fishData.remove(fish);
            fishDAO.deleteFish(fish);
        }
    }

    public void editFishAction() {
        Fish fish = tableFish.getSelectionModel().getSelectedItem();
        FishEditController.data = fishData;
        controller.openAddAndEditFishTable(
                fish, pane, "Окно редактирования рыбы",
                "/fxml/editform/FishEditAdmin.fxml"
        );
    }

    public void editLakeAction() {
        Lake lake = lakeTable.getSelectionModel().getSelectedItem();
        LakeEditController.data = lakeData;
        controller.openAddAndEditLakeTable(
                lake, pane, "Окно редактирования озера",
                "/fxml/editform/LakeEditAdmin.fxml"
        );
    }

    public void addLakeAction() {
        LakeEditController.data = lakeData;
        controller.openAddAndEditLakeTable(
                null, pane, "Окно добавления рыбы",
                "/fxml/editform/LakeEditAdmin.fxml"
        );
    }

    public void deleteLakeAction() {
        Lake lake = lakeTable.getSelectionModel().getSelectedItem();
        if (lake != null) {
            lakeData.remove(lake);
            lakeDAO.deleteLake(lake);
        }
    }

    public void editLureAction() {
        Lure lure = tableBait.getSelectionModel().getSelectedItem();
        LureEditController.data = lureData;
        controller.openAddAndEditLureTable(
                lure, pane, "Окно редактирования наживки",
                "/fxml/editform/LureEditAdmin.fxml"
        );
    }

    public void addLureAction() {
        LureEditController.data = lureData;
        controller.openAddAndEditLureTable(
                null, pane, "Окно добавления наживки",
                "/fxml/editform/LureEditAdmin.fxml"
        );
    }

    public void deleteLureAction() {
        Lure lure = tableBait.getSelectionModel().getSelectedItem();
        if (lure != null) {
            lureData.remove(lure);
            lureDAO.deleteLure(lure);
        }
    }

}
