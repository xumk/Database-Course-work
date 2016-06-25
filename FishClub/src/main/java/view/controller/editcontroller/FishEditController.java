package view.controller.editcontroller;

import controllers.UserLogicController;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.dbservice.dao.FishDAO;
import modal.entity.Fish;
import view.AlertMessage;
import view.listeners.DoubleFormatListener;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

/**
 * Created by Алексей on 16.06.2016.
 */
public class FishEditController implements Initializable {
    public static Stage stage;
    public static Pane parentPane;
    public static ObservableList<Fish> data;
    public static Fish fish;

    public TextField fishName;
    public TextField fishFamily;
    public TextField fishMin;
    public TextField fishMax;
    public TextField fishDepth;
    public Button okButton;
    public Text headerEditFish;
    public Button closeButton;

    private FishDAO fishDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fishDAO = UserLogicController.factory.getFishDAO();
        fishDepth.textProperty().addListener(new DoubleFormatListener(fishDepth));;
        fishMin.textProperty().addListener(new DoubleFormatListener(fishMin));
        fishMax.textProperty().addListener(new DoubleFormatListener(fishMax));
        stage.setOnCloseRequest(we ->
                parentPane.setDisable(false));
        if (fish != null) {
            headerEditFish.setText("Редактирование рыбы");
            fishName.setText(fish.getName());
            fishFamily.setText(fish.getFamily());
            fishMin.setText(String.valueOf(fish.getMinWeight()));
            fishMax.setText(String.valueOf(fish.getMaxWeight()));
            fishDepth.setText(String.valueOf(fish.getDepthLiving()));
        }
    }

    public void okAction() {
        Fish newFish = new Fish();
        newFish.setName(fishName.getText());
        newFish.setFamily(fishFamily.getText());
        newFish.setMinWeight(Double.valueOf(fishMin.getText()));
        newFish.setMaxWeight(Double.valueOf(fishMax.getText()));
        newFish.setDepthLiving(Double.valueOf(fishDepth.getText()));
        if (fish == null) {
            boolean fishThere = data.stream().map(Fish::getName)
                    .anyMatch(newFish.getName()::equals);

            if (fishThere) {
                new AlertMessage(
                        "Не верное добавление",
                        "Данная рыба уже существует",
                        null,
                        INFORMATION
                );
            } else {
                fishDAO.addFish(newFish);
                data.add(newFish);
            }
        } else {
            int index = data.indexOf(fish);
            data.set(index, newFish);
            newFish.setId(fish.getId());
            fishDAO.updateFish(newFish);
        }
        closeAction();
    }

    public void closeAction() {
        stage.fireEvent(new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST
        ));
    }
}
