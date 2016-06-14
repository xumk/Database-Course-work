package view;

import javafx.scene.control.Alert;

import static javafx.scene.control.Alert.*;

/**
 * Created by Алексей on 14.06.2016.
 */
public class AlertMessage {
    public AlertMessage(String title, String header, String contet, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contet);
        alert.showAndWait();
    }
}
