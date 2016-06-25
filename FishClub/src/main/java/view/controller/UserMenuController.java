package view.controller;

import controllers.UserLogicController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import modal.dbservice.dao.*;
import modal.dbservice.daoimpl.joindao.AvailabilityDAO;
import modal.dbservice.daoimpl.joindao.DistanceDAO;
import modal.dbservice.daoimpl.joindao.PrefersDAO;
import modal.entity.Fish;
import modal.entity.Lake;
import modal.entity.Lure;
import modal.entity.User;
import modal.entity.joinentity.Availability;
import modal.entity.joinentity.Distance;
import modal.entity.joinentity.Prefers;
import report.LakeReport;
import report.LureReport;
import report.view.FishViewReport;
import report.view.LakeAndFishViewReport;
import report.view.LureViewReport;
import view.listeners.NumericFormatListener;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;

/**
 * Created by Алексей on 13.06.2016.
 */
public class UserMenuController implements Initializable {
    public static Stage stage;
    public static User user;
    public Button exit;
    public TableColumn<Distance, Double> lakeDistance;
    public TableColumn<Distance, String> lakeName;
    public TableView<Distance> lakeTable;
    public TableColumn<Prefers, String> fishName;
    public TableView<Prefers> tableFish;
    public TableColumn<Availability, String> baitName;
    public TableView<Availability> tableBait;
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
    public TableColumn<Availability, Integer> baitCount;
    public ToggleButton informLureButton;
    public ToggleButton informFishButton;
    public ToggleButton informLakeButton;
    public Pane pane;
    public Pane paneInformLure;
    public Label countHook;
    public Label weight;
    public Label depthDown;
    public Label isLife;
    public Pane paneInformFish;
    public Label family;
    public Label minWeight;
    public Label maxWeight;
    public Label depthLiving;
    public Pane paneInformLake;
    public Label areaLake;
    public Label depthLake;

    private ObservableList<String> genders = observableArrayList("Мужской", "Женский");
    private UserLogicController controller;
    private ObservableList<Availability> lureData;
    private ObservableList<Prefers> fishData;
    private ObservableList<Distance> lakeData;

    private FishDAO fishDAO;
    private PrefersDAO prefersDAO;
    private AvailabilityDAO availabilityDAO;
    private DistanceDAO distanceDAO;
    private FisherDAO fisherDAO;
    private LureDAO lureDAO;
    private UserDAO userDAO;
    private LakeDAO lakeDAO;

    private ObservableList<Fish> allFish;
    private ObservableList<Lure> allLure;
    private ObservableList<Lake> allLake;

    public UserMenuController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        controller = UserLogicController.instance();
        fishDAO = UserLogicController.factory.getFishDAO();
        prefersDAO = UserLogicController.factory.getPrefersDAO();
        distanceDAO = UserLogicController.factory.getDistanceDAO();
        availabilityDAO = UserLogicController.factory.getAvailabilityDAO();
        userDAO = UserLogicController.factory.getUserDAO();
        fisherDAO = UserLogicController.factory.getFisherDAO();
        lureDAO = UserLogicController.factory.getLureDAO();
        lakeDAO = UserLogicController.factory.getLakeDAO();

        allFish = observableArrayList(fishDAO.getAllFishs());
        allLure = observableArrayList(lureDAO.getAllLures());
        allLake = observableArrayList(lakeDAO.getAllLake());

        this.genderComboBox.setItems(this.genders);
        stage.setOnCloseRequest(we ->
                UserLogicController.service.closeSessionFactory()
        );
        initTableFishsForUser();
        initTableLureForUser();
        initTableLakeForUser();
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

    private void initTableLureForUser() {
        //Выбираем по таблице "Наличие"
        List<Availability> lures = user.getFisher().getLures();
        baitName.setCellValueFactory(new PropertyValueFactory<>("lure"));
        baitCount.setCellValueFactory(new PropertyValueFactory<>("countLure"));
        lureData = observableArrayList(lures);
        tableBait.setItems(lureData);
    }

    private void initTableFishsForUser() {
        //Рыба
        List<Prefers> fishs = user.getFisher().getFishs();
        fishName.setCellValueFactory(new PropertyValueFactory<>("fish"));
        fishData = observableArrayList(fishs);
        tableFish.setItems(fishData);
    }

    private void initTableLakeForUser() {
        //Выбираем по таблице "Расстояние"
        List<Distance> lakes = user.getFisher().getLakes();
        lakeName.setCellValueFactory(new PropertyValueFactory<>("lake"));
        lakeDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        lakeData = observableList(lakes);
        lakeTable.setItems(lakeData);
    }

    public void addFish() {
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
        fishComboBox.setItems(allFish);
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setDefaultButton(true);
        buttonOk.setOnAction((ActionEvent evt) -> {
            Fish fish = fishDAO.getFishById(fishComboBox.getValue().getId());
            if (fish != null) {
                Prefers prefers = new Prefers(fish, user.getFisher());
                if (!fishData.contains(prefers)) {
                    fishData.add(prefers);
                    prefersDAO.addObject(prefers);
                    user.getFisher().getFishs().add(prefers);
                    fish.getFishers().add(prefers);
                    fishDAO.updateFish(fish);
                    fisherDAO.updateFisher(user.getFisher());
                    userDAO.updateUser(user);
                }
            }
            closeDialog(dialog);
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            closeDialog(dialog);
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(new Label("Выберите рыбу"), fishComboBox, buttons);
        Scene scene = new Scene(box, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    public void addLure() {
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
        ComboBox<Lure> fishComboBox = new ComboBox<>();
        fishComboBox.setItems(allLure);
        TextField countLure = new TextField();
        countLure.textProperty()
                .addListener(new NumericFormatListener(countLure));
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setDefaultButton(true);
        buttonOk.setOnAction((ActionEvent evt) -> {
            Lure lure = lureDAO.getLureById(fishComboBox.getValue().getId());
            if (lure != null) {
                int count = countLure.getText() != null
                        && !countLure.getText().isEmpty() ?
                        Integer.parseInt(countLure.getText())
                        : 0;
                Availability availability = new Availability(user.getFisher(), lure, count);
                if (!lureData.contains(availability)) {
                    lureData.add(availability);
                    availabilityDAO.addObject(availability);
                    user.getFisher().getLures().add(availability);
                    lure.getFishers().add(availability);
                    lureDAO.updateLure(lure);
                    fisherDAO.updateFisher(user.getFisher());
                    userDAO.updateUser(user);
                }
            }
            closeDialog(dialog);
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            closeDialog(dialog);
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(new Label("Выберите наживку"), fishComboBox,
                new Label("Количество"), countLure, buttons);
        Scene scene = new Scene(box, 300, 150);
        dialog.setScene(scene);
        dialog.show();
    }

    public void addLake() {
        pane.setDisable(true);
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Окно добавления озера");
        dialog.setOnCloseRequest(evt -> {
            pane.setDisable(false);
        });
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        ComboBox<Lake> lakeComboBox = new ComboBox<>();
        lakeComboBox.setItems(allLake);
        TextField distance = new TextField();
        distance.textProperty()
                .addListener(new NumericFormatListener(distance));
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setDefaultButton(true);
        buttonOk.setOnAction((ActionEvent evt) -> {
            Lake lake = lakeDAO.getLakeById(lakeComboBox.getValue().getId());
            if (lake != null) {
                double count = distance.getText() != null
                        && !distance.getText().isEmpty() ?
                        Double.parseDouble(distance.getText())
                        : 0d;
                Distance distance1 = new Distance(user.getFisher(), lake, count);
                if (!lakeData.contains(distance1)) {
                    lakeData.add(distance1);
                    distanceDAO.addObject(distance1);
                    //user.getFisher().getLakes().add(distance1);
                    lake.getFishers().add(distance1);
                    lakeDAO.updateLake(lake);
                    fisherDAO.updateFisher(user.getFisher());
                    userDAO.updateUser(user);
                }
            }
            closeDialog(dialog);
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            closeDialog(dialog);
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(new Label("Выберите озеро"), lakeComboBox,
                new Label("Расстояние"), distance, buttons);
        Scene scene = new Scene(box, 300, 150);
        dialog.setScene(scene);
        dialog.show();
    }

    public void editLake() {
        if (lakeTable.getSelectionModel().getSelectedItem() != null) {
            pane.setDisable(true);
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно редактирования озера");
            dialog.setOnCloseRequest(evt -> {
                pane.setDisable(false);
            });
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField distanceLake = new TextField();
            distanceLake.setText(lakeDistance.getCellObservableValue(0).getValue().toString());
            distanceLake.textProperty()
                    .addListener(new NumericFormatListener(distanceLake));
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setDefaultButton(true);
            buttonOk.setOnAction((ActionEvent evt) -> {
                Distance distance = lakeTable.getSelectionModel().getSelectedItem();
                if (distance != null) {
                    double count = distanceLake.getText() != null
                            && !distanceLake.getText().isEmpty() ?
                            Double.parseDouble(distanceLake.getText())
                            : 0d;
                    distance.setDistance(count);
                    lakeData.set(lakeData.indexOf(distance), distance);
                    distanceDAO.updateObject(distance);
                }
                closeDialog(dialog);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setOnAction(evt -> {
                closeDialog(dialog);
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            box.getChildren().addAll(
                    new Label("Расстояние"), distanceLake, buttons
            );
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        }
    }

    public void editLure() {
        if (tableBait.getSelectionModel().getSelectedItem() != null) {
            pane.setDisable(true);
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно редактирования наживки");
            dialog.setOnCloseRequest(evt -> {
                pane.setDisable(false);
            });
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField countFish = new TextField();
            countFish.textProperty()
                    .addListener(new NumericFormatListener(countFish));
            countFish.setText(baitCount.getCellObservableValue(0).getValue().toString());

            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setDefaultButton(true);
            buttonOk.setOnAction((ActionEvent evt) -> {
                Availability availability = tableBait.getSelectionModel().getSelectedItem();
                if (availability != null) {
                    int count = countFish.getText() != null
                            && !countFish.getText().isEmpty() ?
                            Integer.parseInt(countFish.getText())
                            : 0;
                    availability.setCountLure(count);
                    lureData.set(lureData.indexOf(availability), availability);
                    availabilityDAO.updateObject(availability);
                }
                closeDialog(dialog);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setOnAction(evt -> {
                closeDialog(dialog);
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            box.getChildren().addAll(
                    new Label("Количество"), countFish, buttons
            );
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        }
    }

    public void deleteFish() {
        if (tableFish.getSelectionModel().getSelectedItem() != null) {
            Prefers prefers = prefersDAO.getById(
                    tableFish.getSelectionModel().getSelectedItem().getId()
            );
            if (prefers != null) {
                fishData.remove(prefers);
                user.getFisher().getFishs().remove(prefers);
                prefers.getFish().getFishers().remove(prefers);
                prefersDAO.deleteObject(prefers);
                fishDAO.updateFish(prefers.getFish());
                fisherDAO.updateFisher(user.getFisher());
                userDAO.updateUser(user);
            }
        }
    }

    public void deleteLure() {
        if (tableBait.getSelectionModel().getSelectedItem() != null) {
            Availability availability = availabilityDAO.getById(
                    tableBait.getSelectionModel().getSelectedItem().getId()
            );
            if (availability != null) {
                lureData.remove(availability);
                user.getFisher().getLures().remove(availability);
                availability.getLure().getFishers().remove(availability);
                availabilityDAO.deleteObject(availability);
                lureDAO.updateLure(availability.getLure());
                fisherDAO.updateFisher(user.getFisher());
                userDAO.updateUser(user);
            }
        }
    }

    public void deleteLake() {
        if (lakeTable.getSelectionModel().getSelectedItem() != null) {
            Distance distance = distanceDAO.getById(
                    lakeTable.getSelectionModel().getSelectedItem().getId()
            );
            if (distance != null) {
                lakeData.remove(distance);
                user.getFisher().getLakes().remove(distance);
                distance.getLake().getFishers().remove(distance);
                distanceDAO.deleteObject(distance);
                lakeDAO.updateLake(distance.getLake());
                fisherDAO.updateFisher(user.getFisher());
                userDAO.updateUser(user);
            }
        }
    }

    private void closeDialog(Stage dialog) {
        dialog.fireEvent(new WindowEvent(
                dialog,
                WindowEvent.WINDOW_CLOSE_REQUEST
        ));
        pane.setDisable(false);
    }

    public void getInformLure() {
        if (informLureButton.isSelected()
                && tableBait.getSelectionModel().getSelectedItem() != null) {
            paneInformLure.setVisible(true);
            Availability availability = tableBait.getSelectionModel().getSelectedItem();
            countHook.setText(String.valueOf(availability.getLure().getCountHooks()));
            weight.setText(String.valueOf(availability.getLure().getWeight()));
            depthDown.setText(String.valueOf(availability.getLure().getDivingDepth()));
            isLife.setText(availability.getLure().isImitation() ?
                    "Искусственная" : "Живая"
            );
        } else {
            paneInformLure.setVisible(false);
            countHook.setText(null);
            weight.setText(null);
            depthDown.setText(null);
        }
    }

    public void getInformLake() {
        if (informLakeButton.isSelected()
                && lakeTable.getSelectionModel().getSelectedItem() != null) {
            paneInformLake.setVisible(true);
            Distance distance = lakeTable.getSelectionModel().getSelectedItem();
            areaLake.setText(String.valueOf(distance.getLake().getArea()));
            depthLake.setText(String.valueOf(distance.getLake().getDepth()));
        } else {
            paneInformLake.setVisible(false);
            areaLake.setText(null);
            depthLake.setText(null);
        }
    }

    public void getInformFish() {
        if (informFishButton.isSelected()
                && tableFish.getSelectionModel().getSelectedItem() != null) {
            paneInformFish.setVisible(true);
            Prefers prefers = tableFish.getSelectionModel().getSelectedItem();
            family.setText(prefers.getFish().getFamily());
            minWeight.setText(String.valueOf(prefers.getFish().getMinWeight()));
            maxWeight.setText(String.valueOf(prefers.getFish().getMaxWeight()));
            depthLiving.setText(String.valueOf(prefers.getFish().getDepthLiving()));
        } else {
            paneInformFish.setVisible(false);
            family.setText(null);
            minWeight.setText(null);
            maxWeight.setText(null);
            depthLiving.setText(null);
        }
    }

    public void dowloadLureReport() {
        ObservableList<Availability> reportData = tableBait.getItems();
        LureReport.writeIntoExcel(reportData);
    }

    public void dowloadLakeReport() {
        ObservableList<Distance> reportData = lakeTable.getItems();
        LakeReport.writeIntoExcel(reportData);
    }

    public void openViewReportLure() {
        LureViewReport.createReport(new Stage(), allLure);
    }

    public void openViewReportFish() {
        FishViewReport.createReport(new Stage(), allFish);
    }

    public void openViewReportLakeAndFish() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Окно выбора озера");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        ComboBox<Lake> lakeComboBox = new ComboBox<>();
        lakeComboBox.setItems(allLake);
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setDefaultButton(true);
        buttonOk.setOnAction((ActionEvent evt) -> {
            Lake lake = lakeComboBox.getValue();
            if (lake != null) {
                LakeAndFishViewReport.createReport(new Stage(), lake);
            }
            dialog.close();
        });
        Button buttonEx = new Button("Cancel");
        buttonEx.setOnAction(evt -> {
            dialog.close();
        });
        buttons.getChildren().addAll(buttonOk, buttonEx);
        box.getChildren().addAll(new Label("Выберите озеро"),
                lakeComboBox, buttons);
        Scene scene = new Scene(box, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }
}
