package view.controller;

import controllers.UserLogicController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.dbservice.dao.*;
import modal.entity.Fish;
import modal.entity.Lake;
import modal.entity.Lure;
import modal.entity.User;
import modal.helpmodal.LivedFishLake;
import view.controller.editcontroller.FishEditController;
import view.controller.editcontroller.LakeEditController;
import view.controller.editcontroller.LureEditController;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            Stage stage = new Stage();
            SingUpController.stage = stage;
            controller.openSingUpMenuScene(
                    AdministratorMenuController.stage, stage,
                    "Окно авторизации", "/fxml/SingUp.fxml"
            );
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
