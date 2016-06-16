package view.controller.editcontroller;

import controllers.UserLogicController;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.dbservice.dao.LakeDAO;
import modal.entity.Lake;
import view.AlertMessage;
import view.listeners.DoubleFormatListener;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

/**
 * Created by Алексей on 17.06.2016.
 */
public class LakeEditController implements Initializable {
    public static Stage stage;
    public static Pane parentPane;
    public static ObservableList<Lake> data;
    public static Lake lake;

    public TextField lakeName;
    public TextField lakeDepth;
    public TextField lakeArea;
    public Text headerLakeEdit;

    private LakeDAO lakeDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lakeDAO = UserLogicController.factory.getLakeDAO();
        lakeDepth.textProperty().addListener(new DoubleFormatListener(lakeDepth));
        lakeArea.textProperty().addListener(new DoubleFormatListener(lakeArea));
        stage.setOnCloseRequest(we ->
                parentPane.setDisable(false));
        if (lake != null) {
            headerLakeEdit.setText("Редактирование озера");
            lakeName.setText(lake.getName());
            lakeDepth.setText(String.valueOf(lake.getDepth()));
            lakeArea.setText(String.valueOf(lake.getArea()));
        }
    }

    public void okAction() {
        Lake newLake = new Lake();
        newLake.setName(lakeName.getText());
        newLake.setDepth(Double.valueOf(lakeDepth.getText()));
        newLake.setArea(Double.valueOf(lakeArea.getText()));
        if (lake == null) {
            boolean lakeThere = data.stream().map(Lake::getName)
                    .anyMatch(newLake.getName()::equals);

            if (lakeThere) {
                new AlertMessage(
                        "Не верное добавление",
                        "Данное озеро уже существует",
                        null,
                        INFORMATION
                );
            } else {
                lakeDAO.addLake(newLake);
                data.add(newLake);
            }
        } else {
            int index = data.indexOf(lake);
            data.set(index, newLake);
            newLake.setId(lake.getId());
            lakeDAO.updateLake(newLake);
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
