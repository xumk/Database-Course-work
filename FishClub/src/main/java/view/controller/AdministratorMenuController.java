package view.controller;

import controllers.UserLogicController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import modal.dbservice.dao.FishDAO;
import modal.dbservice.dao.LakeDAO;
import modal.dbservice.dao.LureDAO;
import modal.dbservice.daoimpl.joindao.*;
import modal.entity.Fish;
import modal.entity.Lake;
import modal.entity.Lure;
import modal.entity.User;
import modal.entity.joinentity.Lived;
import modal.entity.joinentity.Peck;
import view.controller.editcontroller.FishEditController;
import view.controller.editcontroller.LakeEditController;
import view.controller.editcontroller.LureEditController;
import view.listeners.NumericFormatListener;

import java.io.IOException;
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
    public TableColumn<Lure, String> nameLureLink;
    public ToggleButton openMasterDetailFishGridButton;
    public TableView<Lived> fishLivedTable;
    public TableColumn<Lived, String> nameFishLink;
    public TableColumn<Lived, Integer> countFishiLake;
    public Button editLakeLinkFishButton;
    public ToggleButton openMasterDetailLakeGridButton;
    public TableView<Peck> linkTableLure;
    public Tab userInformTab;

    private ObservableList<String> genders = observableArrayList("Мужской", "Женский");
    private LakeDAO lakeDAO;
    private LureDAO lureDAO;
    private FishDAO fishDAO;
    private LivedDAO livedDAO;
    private PeckDAO peckDAO;
    private PrefersDAO prefersDAO;
    private AvailabilityDAO availabilityDAO;
    private DistanceDAO distanceDAO;
    private ObservableList<Lake> lakeData;
    private ObservableList<Lure> lureData;
    private ObservableList<Fish> fishData;
    private ObservableList<Peck> linkLureData;
    private ObservableList<Lived> linkFishData;
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
        livedDAO = UserLogicController.factory.getLivedDAO();
        peckDAO = UserLogicController.factory.getPeckDAO();
        distanceDAO = UserLogicController.factory.getDistanceDAO();
        prefersDAO = UserLogicController.factory.getPrefersDAO();
        availabilityDAO = UserLogicController.factory.getAvailabilityDAO();

        this.genderComboBox.setItems(this.genders);
        this.controller = UserLogicController.instance();

        openMasterDetailFishGridButton.setOnAction(evt -> {
            if (openMasterDetailFishGridButton.isSelected()
                    && tableFish.getSelectionModel().getSelectedItem() != null) {
                currentFish = tableFish.getSelectionModel().getSelectedItem();
                currentFish = fishDAO.getFishById(currentFish.getId());
                linkLureData = observableArrayList(currentFish.getLures());
                nameLureLink.setCellValueFactory(new PropertyValueFactory<>("lure"));
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
                currentLake = lakeDAO.getLakeById(currentLake.getId());
                linkFishData = observableArrayList(currentLake.getFishs());
                //controller.createLinkFish(currentLake.getFishs(), currentLake);
                nameFishLink.setCellValueFactory(new PropertyValueFactory<>("fish"));
                countFishiLake.setCellValueFactory(new PropertyValueFactory<>("countFish"));
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
        if (user != null) {
            this.fillInformationOnUserData(user);
        } else {
            userInformTab.setText("Регистрация администраторов");
            userInformTab.getContent().setVisible(false);
            Button button = new Button("Зарегистрировать");
            userInformTab.setContent(button);
            button.setOnAction(evt -> {
                Stage stage = new Stage();
                Parent root = null;
                try {
                    RegistryController.STAGE = stage;
                    RegistryController.parentPane = this.pane;
                    RegistryController.isSystem = true;
                    root = FXMLLoader.load(this.getClass().getResource("/fxml/Registry.fxml"));
                    Scene e = new Scene(root, 550.0D, 400.0D);
                    this.pane.setDisable(true);
                    stage.setTitle("Окно добавления администратора");
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(e);
                    stage.show();
                } catch (IOException var5) {
                    var5.printStackTrace();
                }
            });
        }
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
        if (tableFish.getSelectionModel().getSelectedItem() != null) {
            Fish fish = fishDAO.getFishById(tableFish.getSelectionModel().getSelectedItem().getId());
            if (fish != null) {
                peckDAO.removeAll(fish.getLures());
                livedDAO.removeAll(fish.getLakes());
                prefersDAO.removeAll(fish.getFishers());
                fishData.remove(tableFish.getSelectionModel().getSelectedItem());
                fishDAO.deleteFish(fish);
            }
        }
    }

    public void editFishAction() {
        Fish fish = tableFish.getSelectionModel().getSelectedItem();
        if (fish != null) {
            FishEditController.data = fishData;
            controller.openAddAndEditFishTable(
                    fish, pane, "Окно редактирования рыбы",
                    "/fxml/editform/FishEditAdmin.fxml"
            );
        }
    }

    public void editLakeAction() {
        Lake lake = lakeTable.getSelectionModel().getSelectedItem();
        if (lake != null) {
            LakeEditController.data = lakeData;
            controller.openAddAndEditLakeTable(
                    lake, pane, "Окно редактирования озера",
                    "/fxml/editform/LakeEditAdmin.fxml"
            );
        }
    }

    public void addLakeAction() {
        LakeEditController.data = lakeData;
        controller.openAddAndEditLakeTable(
                null, pane, "Окно добавления рыбы",
                "/fxml/editform/LakeEditAdmin.fxml"
        );
    }

    public void deleteLakeAction() {
        if (lakeTable.getSelectionModel().getSelectedItem() != null) {
            Lake lake = lakeDAO.getLakeById(lakeTable.getSelectionModel().getSelectedItem().getId());
            if (lake != null) {
                livedDAO.removeAll(lake.getFishs());
                distanceDAO.removeAll(lake.getFishers());
                lakeData.remove(lakeTable.getSelectionModel().getSelectedItem());
                lakeDAO.deleteLake(lake);
            }
        }
    }

    public void editLureAction() {
        Lure lure = tableBait.getSelectionModel().getSelectedItem();
        if (lure != null) {
            LureEditController.data = lureData;
            controller.openAddAndEditLureTable(
                    lure, pane, "Окно редактирования наживки",
                    "/fxml/editform/LureEditAdmin.fxml"
            );
        }
    }

    public void addLureAction() {
        LureEditController.data = lureData;
        controller.openAddAndEditLureTable(
                null, pane, "Окно добавления наживки",
                "/fxml/editform/LureEditAdmin.fxml"
        );
    }

    public void deleteLureAction() {
        if (tableBait.getSelectionModel().getSelectedItem() != null) {
            Lure lure = lureDAO.getLureById(tableBait.getSelectionModel().getSelectedItem().getId());
            if (lure != null) {
                peckDAO.removeAll(lure.getFishs());
                availabilityDAO.removeAll(lure.getFishers());
                lureData.remove(tableBait.getSelectionModel().getSelectedItem());
                lureDAO.deleteLure(lure);
            }
        }
    }

    public void addLinkPeck() {
        pane.setDisable(true);
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Окно добавления наживки");
        dialog.setOnCloseRequest(evt -> {
            pane.setDisable(false);
        });
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        ComboBox<Lure> lureComboBox = new ComboBox<>();
        lureComboBox.setItems(lureData);
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setDefaultButton(true);
        buttonOk.setOnAction((ActionEvent evt) -> {
            Lure lure = lureComboBox.getValue();
            if (lure != null) {
                Peck peck = new Peck(currentFish, lure);
                if (!linkLureData.contains(peck)) {
                    linkLureData.add(peck);
                    peckDAO.addObject(peck);
                    lure.getFishs().add(peck);
                    currentFish.getLures().add(peck);
                    fishDAO.updateFish(currentFish);
                    lureDAO.updateLure(lure);
                }
            }
            closeDialog(dialog);
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            closeDialog(dialog);
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(
                new Label("Выберите наживку"),
                lureComboBox, buttons
        );
        Scene scene = new Scene(box, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    public void addLinkLived() {
        pane.setDisable(true);
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Окно добавления рыбы");
        dialog.setOnCloseRequest(evt -> {
            pane.setDisable(false);
        });
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        ComboBox<Fish> fishComboBox = new ComboBox<>();
        fishComboBox.setItems(fishData);
        TextField countFish = new TextField();
        countFish.textProperty()
                .addListener(new NumericFormatListener(countFish));
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setDefaultButton(true);
        buttonOk.setOnAction((ActionEvent evt) -> {
            Fish fish = fishComboBox.getValue();
            if (fish != null) {
                int count = countFish.getText() != null
                        && !countFish.getText().isEmpty() ?
                        Integer.parseInt(countFish.getText())
                        : 0;
                Lived lived = new Lived(fish, currentLake, count);
                if (!linkFishData.contains(lived)) {
                    linkFishData.add(lived);
                    livedDAO.addObject(lived);
                    currentLake.getFishs().add(lived);
                    fish.getLakes().add(lived);
                    lakeDAO.updateLake(currentLake);
                    fishDAO.updateFish(fish);
                }
            }
            closeDialog(dialog);
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            closeDialog(dialog);
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(
                new Label("Выберите рыбу"), fishComboBox,
                new Label("Популяция"), countFish, buttons
        );
        Scene scene = new Scene(box, 300, 150);
        dialog.setScene(scene);
        dialog.show();
    }

    public void deleteLinkPeck() {
        Peck peck = linkTableLure.getSelectionModel().getSelectedItem();
        if (peck != null) {
            linkLureData.remove(peck);
            currentFish.getLures().remove(peck);
            fishDAO.updateFish(currentFish);
        }
    }

    private void closeDialog(Stage dialog) {
        dialog.fireEvent(new WindowEvent(
                dialog,
                WindowEvent.WINDOW_CLOSE_REQUEST
        ));
        pane.setDisable(false);
    }

    public void deleteLinkLived() {
        Lived linkFish = fishLivedTable.getSelectionModel().getSelectedItem();
        if (linkFish != null) {
            linkFishData.remove(linkFish);
            Lived lived = livedDAO.getLivedByFishAndLakeId(linkFish.getFish().getId(), currentLake.getId());
            livedDAO.deleteObject(lived);
            currentLake.getFishs().remove(lived);
            lakeDAO.updateLake(currentLake);
        }
    }

    public void editLinkLived() {
        if (fishLivedTable.getSelectionModel().getSelectedItem() != null) {
            pane.setDisable(true);
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно редактирования рыбы");
            dialog.setOnCloseRequest(evt -> {
                pane.setDisable(false);
            });
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField countFish = new TextField();
            countFish.textProperty()
                    .addListener(new NumericFormatListener(countFish));
            countFish.setText(countFishiLake.getCellObservableValue(0).getValue().toString());
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setDefaultButton(true);
            buttonOk.setOnAction((ActionEvent evt) -> {
                Lived lived = fishLivedTable.getSelectionModel().getSelectedItem();
                if (lived != null) {
                    int count = countFish.getText() != null
                            && !countFish.getText().isEmpty() ?
                            Integer.parseInt(countFish.getText())
                            : 0;
                    lived.setCountFish(count);
                    linkFishData.set(linkFishData.indexOf(lived), lived);
                    livedDAO.updateObject(lived);
                }
                closeDialog(dialog);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setOnAction(evt -> {
                closeDialog(dialog);
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            box.getChildren().addAll(
                    new Label("Популяция"), countFish, buttons
            );
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        }
    }
}
