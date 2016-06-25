package view;

import javafx.scene.control.Alert;

import static javafx.scene.control.Alert.*;

/**
 * Класс для визуально отображения ошибок или
 * сообщений для пользователя
 */
public class AlertMessage {
    /**
     * Конструктор класса
     *
     * @param title  Заголовок
     * @param header сообщение
     * @param contet расшифровка сообщения
     * @param type   тип сообщения
     */
    public AlertMessage(String title, String header, String contet, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contet);
        alert.showAndWait();
    }
}
