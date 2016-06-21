package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modal.dbservice.DAOFactory;
import modal.dbservice.DBService;
import modal.dbservice.daoimpl.joindao.AvailabilityDAO;
import modal.dbservice.daoimpl.joindao.DistanceDAO;
import modal.dbservice.daoimpl.joindao.LivedDAO;
import modal.entity.*;
import modal.entity.agregation.Gender;
import modal.entity.joinentity.Availability;
import modal.entity.joinentity.Distance;
import modal.entity.joinentity.Lived;
import modal.helpmodal.AvailabilityFisherLure;
import modal.helpmodal.DistanceLakeFisher;
import modal.helpmodal.LivedFishLake;
import view.AlertMessage;
import view.controller.AdministratorMenuController;
import view.controller.SingUpController;
import view.controller.UserMenuController;
import view.controller.editcontroller.FishEditController;
import view.controller.editcontroller.LakeEditController;
import view.controller.editcontroller.LureEditController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public void openSingUpMenuScene(Stage parentStage, Stage stage, String title, String url) {
        try {
            if (stage != null) {
                SingUpController.stage = stage;
            }
            Parent e = FXMLLoader.load(this.getClass().getResource(url));
            Scene scene = new Scene(e, 550, 400);
            stage.setTitle(title);
            stage.setScene(scene);
            if (parentStage != null) {
                parentStage.close();
            }
            stage.show();
        } catch (IOException var4) {
            var4.printStackTrace();
        }
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
        openScene(parentStage, stage, title, url, 700, 450);
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

    public ObservableList<LivedFishLake> createLinkFish(List<Fish> fishs, Lake currentLake) {
        List<LivedFishLake> livedFishLakes = new ArrayList<>();
        LivedDAO dao = factory.getLivedDAO();
        for (Fish fish : fishs) {
            LivedFishLake link = new LivedFishLake();
            link.setNameFish(fish.getName());
            link.setIdFish(fish.getId());
            Lived lived = dao.getLivedByFishAndLakeId(
                    fish.getId(),
                    currentLake.getId()
            );
            link.setCountFishLived(lived.getCountFish());
            livedFishLakes.add(link);
        }
        ObservableList<LivedFishLake> result = FXCollections.observableArrayList(livedFishLakes);
        return result;
    }

    public void edit(TextField userName, PasswordField password,
                     TextField firstName, TextField middleName,
                     TextField lastName, DatePicker birthDate,
                     ComboBox genderComboBox) {
        lastName.setEditable(true);
        userName.setEditable(true);
        firstName.setEditable(true);
        birthDate.setDisable(false);
        middleName.setEditable(true);
        password.setEditable(true);
        genderComboBox.setDisable(false);
    }

    public void fillInformUser(User user, TextField firstName,
                               TextField middleName, TextField lastName,
                               TextField userName, PasswordField password,
                               DatePicker birthDate, ComboBox genderComboBox) {
        Fisher fisher = user.getFisherman();
        lastName.setText(fisher.getLastName());
        userName.setText(user.getLogin());
        firstName.setText(fisher.getName());
        birthDate.setValue(ConvertionHelper.convertDataToLocalDate(fisher.getBirthDay()));
        middleName.setText(fisher.getMiddleName());
        password.setText(user.getPassword());
        if (fisher.getGender() == Gender.MAN) {
            genderComboBox.setValue("Мужской");
        } else {
            genderComboBox.setValue("Женский");
        }
    }

    public void updateInforUser(User user, TextField userName,
                                PasswordField password, TextField firstName,
                                TextField middleName, TextField lastName,
                                DatePicker birthDate, ComboBox genderComboBox) {
        if (lastName.isEditable()) {
            Fisher fisher = user.getFisherman();
            Date birthDateConvert = ConvertionHelper.convertLocalDateToDate(birthDate.getValue());
            Gender gender = genderComboBox.getValue().equals("Мужской") ? Gender.MAN : Gender.WOMAN;
            if (!user.getLogin().equals(userName.getText())
                    || !user.getPassword().equals(password.getText())
                    || !fisher.getName().equals(firstName.getText())
                    || !fisher.getMiddleName().equals(middleName.getText())
                    || !fisher.getLastName().equals(lastName.getText())
                    || !fisher.getBirthDay().equals(birthDateConvert)
                    || !fisher.getGender().equals(gender)) {
                user.setLogin(userName.getText());
                user.setPassword(password.getText());
                fisher.setName(firstName.getText());
                fisher.setMiddleName(middleName.getText());
                fisher.setLastName(lastName.getText());
                fisher.setBirthDay(birthDateConvert);
                fisher.setGender(gender);

                factory.getFisherDAO().updateFisher(fisher);
                user.setFisherman(fisher);
                factory.getUserDAO().updateUser(user);

                lastName.setEditable(false);
                userName.setEditable(false);
                firstName.setEditable(false);
                birthDate.setDisable(true);
                middleName.setEditable(false);
                password.setEditable(false);
                genderComboBox.setDisable(true);
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

    public ObservableList<AvailabilityFisherLure> createLinkAvailability(List<Lure> lure, Fisher fisher) {
        List<AvailabilityFisherLure> availabilityFishLakes = new ArrayList<>();
        AvailabilityDAO dao = factory.getAvailabilityDAO();
        for (Lure lures: lure) {
            AvailabilityFisherLure link = new AvailabilityFisherLure();
            link.setNameLure(lures.getName());
            Availability availability = dao.getAvailabilityByLureAndFisherId(fisher.getId(),lures.getId());
            link.setCountLure(availability.getCountLure());
            availabilityFishLakes.add(link);
        }
        ObservableList<AvailabilityFisherLure> result = FXCollections.observableArrayList(availabilityFishLakes);
        return result;
    }

    public ObservableList<DistanceLakeFisher> createLinkDistance(List<Lake> lakes, Fisher fisher) {
        List<DistanceLakeFisher> distanceLakeFish = new ArrayList<>();
        DistanceDAO dao = factory.getDistanceDAO();
        for (Lake lake: lakes) {
            DistanceLakeFisher link = new DistanceLakeFisher();
            link.setLakeName(lake.getName());
            Distance distance = dao.getDistanceByLakeAndFisherId(fisher.getId(),lake.getId());
            link.setLakeDistance(distance.getDistance());
            distanceLakeFish.add(link);
        }
        ObservableList<DistanceLakeFisher> result = FXCollections.observableArrayList(distanceLakeFish);
        return result;
    }
}
