package view.controller.editcontroller;

import controllers.UserLogicController;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.dbservice.dao.LureDAO;
import modal.entity.Lure;
import view.AlertMessage;
import view.listeners.DoubleFormatListener;
import view.listeners.NumericFormatListener;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

/**
 * Created by Алексей on 17.06.2016.
 */
public class LureEditController implements Initializable {
    public static Stage stage;
    public static Pane parentPane;
    public static ObservableList<Lure> data;
    public static Lure lure;

    public Text headerLureEdit;
    public CheckBox lureIsLive;
    public TextField lureDepth;
    public TextField lureWeight;
    public TextField lureCountHooks;
    public TextField lureName;

    private LureDAO lureDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lureDAO = UserLogicController.factory.getLureDAO();
        lureDepth.textProperty().addListener(new DoubleFormatListener(lureDepth));
        lureWeight.textProperty().addListener(new DoubleFormatListener(lureWeight));
        lureCountHooks.textProperty().addListener(new NumericFormatListener(lureCountHooks));
        stage.setOnCloseRequest(we ->
                parentPane.setDisable(false));
        if (lure != null) {
            headerLureEdit.setText("Редактирование наживки");
            lureName.setText(lure.getName());
            lureIsLive.setSelected(lure.isImitation());
            lureCountHooks.setText(String.valueOf(lure.getCountHooks()));
            lureWeight.setText(String.valueOf(lure.getWeight()));
            lureDepth.setText(String.valueOf(lure.getDivingDepth()));
        }
    }

    public void okAction() {
        Lure newLure = new Lure();
        newLure.setName(lureName.getText());
        newLure.setImitation(lureIsLive.isSelected());
        newLure.setCountHooks(Integer.valueOf(lureCountHooks.getText()));
        newLure.setWeight(Double.valueOf(lureWeight.getText()));
        newLure.setDivingDepth(Double.valueOf(lureDepth.getText()));
        if (lure == null) {
            boolean lureThere = data.stream().map(Lure::getName)
                    .anyMatch(newLure.getName()::equals);

            if (lureThere) {
                new AlertMessage(
                        "Не верное добавление",
                        "Данная наживка уже существует",
                        null,
                        INFORMATION
                );
            } else {
                lureDAO.addLure(newLure);
                data.add(newLure);
            }
        } else {
            int index = data.indexOf(lure);
            data.set(index, newLure);
            newLure.setId(lure.getId());
            lureDAO.updateLure(newLure);
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
