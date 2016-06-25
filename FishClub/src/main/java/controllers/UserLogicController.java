package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modal.dbservice.DAOFactory;
import modal.dbservice.DBService;
import modal.entity.*;
import modal.entity.agregation.Gender;
import view.AlertMessage;
import view.controller.AdministratorMenuController;
import view.controller.SingUpController;
import view.controller.UserMenuController;
import view.controller.editcontroller.FishEditController;
import view.controller.editcontroller.LakeEditController;
import view.controller.editcontroller.LureEditController;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Класс с логикой работы для пользователей
 * создан для устранения дублежей в коде.
 * Является синглтоном, т.к. нужен в единственном экземпляре
 */
public class UserLogicController {
    public static DBService service;
    public static DAOFactory factory;

    private static UserLogicController instance;
    private static Logger logger = Logger.getLogger(UserLogicController.class.getName());

    /**
     * Приватный конструктор, чтобы никто, кроме самого класса
     * не мог создать его экземпляр
     */
    private UserLogicController() {
    }

    public synchronized static UserLogicController instance() {
        if (instance == null) {
            instance = new UserLogicController();
        }
        return instance;
    }

    /**
     * Открыть сцену входа в приложение
     *
     * @param parentStage предыдущая сцена
     * @param stage
     * @param title       заголовок
     * @param url         адрес fxml файла
     */
    public void openSingUpMenuScene(Stage parentStage, Stage stage, String title, String url) {
        try {
            if (stage != null) {
                SingUpController.stage = stage;
            }
            Parent e = FXMLLoader.load(this.getClass().getResource(url));
            Scene scene = new Scene(e, 550, 400);
            // устанавливаем заголовк окна
            stage.setTitle(title);
            stage.setScene(scene);
            if (parentStage != null) {
                parentStage.close();
            }
            stage.show();
        } catch (IOException var4) {
            // вывод ошибок в лог
            logger.severe(Arrays.toString(var4.getStackTrace()));
        }
    }

    /**
     * Метод открытия окна приложения для пользователя
     *
     * @param parentStage родительская сцена
     * @param title       заголовок окна
     * @param url         путь до файла fxml
     * @param user        юзер, заходящий в систему
     */
    public void openUserMenuScene(Stage parentStage, String title, String url, User user) {
        Stage stage = new Stage();
        UserMenuController.stage = stage;
        UserMenuController.user = user;
        openScene(parentStage, stage, title, url, 700, 400);
    }

    /**
     * Метод для откртытия окна приложения для работы администратора
     *
     * @param parentStage родительская сцена
     * @param title       заголовок окна
     * @param url         путь д о fxml файла
     * @param user        пользователь, входящий в систему
     */
    public void openAdministratorMenuScene(Stage parentStage, String title, String url, User user) {
        Stage stage = new Stage();
        AdministratorMenuController.stage = stage;
        AdministratorMenuController.user = user;
        openScene(parentStage, stage, title, url, 700, 450);
    }

    /**
     * Открыть окно редактирования записи из таблицы "Рыбы"
     * так же является овном добавления записи в таблицу
     *
     * @param fish       раба, которую нужно добавить иди отредактировать
     * @param parentPane предыдущая сцега
     * @param title      заголовок окна
     * @param url        путь до fxml файла
     */
    public void openAddAndEditFishTable(Fish fish, Pane parentPane, String title, String url) {
        Stage stage = new Stage();
        FishEditController.stage = stage;
        FishEditController.parentPane = parentPane;
        FishEditController.fish = fish;
        // открываем сцену
        openScene(null, stage, title, url, 216, 349);
        parentPane.setDisable(true);
    }

    /**
     * Открыть окно редактирования записи из таблицы "Озера"
     * так же является овном добавления записи в таблицу
     *
     * @param lake       озеро, которое нужно добавить иди отредактировать
     * @param parentPane предыдущая сцега
     * @param title      заголовок окна
     * @param url        путь до fxml файла
     */
    public void openAddAndEditLakeTable(Lake lake, Pane parentPane, String title, String url) {
        Stage stage = new Stage();
        LakeEditController.stage = stage;
        LakeEditController.parentPane = parentPane;
        LakeEditController.lake = lake;
        //открытие сцена
        openScene(null, stage, title, url, 229, 259);
        parentPane.setDisable(true);
    }

    /**
     * Открыть окно редактирования записи из таблицы "Наживки"
     * так же является овном добавления записи в таблице
     *
     * @param lure       наживка, которую нужно добавить иди отредактировать
     * @param parentPane предыдущая сцега
     * @param title      заголовок окна
     * @param url        путь до fxml файла
     */
    public void openAddAndEditLureTable(Lure lure, Pane parentPane, String title, String url) {
        Stage stage = new Stage();
        LureEditController.stage = stage;
        LureEditController.parentPane = parentPane;
        LureEditController.lure = lure;
        // открытие сцены
        openScene(null, stage, title, url, 271, 397);
        parentPane.setDisable(true);
    }

    /**
     * Метод открытия сцены
     *
     * @param closeStage сцена, которую нужно закрыть
     * @param newStage   новая сцена
     * @param title      заголовок окна
     * @param url        путь до fxml файла
     * @param width      ширина окна
     * @param height     высота окна
     */
    private void openScene(
            Stage closeStage, Stage newStage, String title, String url,
            double width, double height) {
        try {
            Parent root = constructFXMLLoader(url).load();
            Scene scene = new Scene(root, width, height);
            newStage.setTitle(title);
            // помещаем объект сцены в stage
            newStage.setScene(scene);
            newStage.show();
            if (closeStage != null) {
                closeStage.close();
            }
        } catch (IOException e) {
            // если возникли ошибки, то пишем лог всю информацию
            logger.severe(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Метод для закрузки fxml файла
     *
     * @param url путь до файла
     * @return загрузчик fxml сцены этого файла
     */
    private FXMLLoader constructFXMLLoader(String url) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getURL(url));
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

        return fxmlLoader;
    }

    /**
     * Метод для получения пути по которому находятся ресурсы
     *
     * @param url путь в строков виде
     * @return класс URL
     */
    private URL getURL(String url) {
        return UserLogicController.class.getResource(url);
    }

    /**
     * Метод разблокирующий все поля, для редактирвоания основной
     * информации о пользователе
     *
     * @param userName       лоигн
     * @param password       пароль
     * @param firstName      имя
     * @param middleName     отчество
     * @param lastName       фамилия
     * @param birthDate      дата рождения
     * @param genderComboBox пол
     */
    public void edit(TextField userName, PasswordField password,
                     TextField firstName, TextField middleName,
                     TextField lastName, DatePicker birthDate,
                     ComboBox genderComboBox) {
        lastName.setEditable(true);
        userName.setEditable(true);
        firstName.setEditable(true);
        birthDate.setDisable(false);
        // разрещить редактировать поле (комбо-бокс) "Отчество"
        middleName.setEditable(true);
        password.setEditable(true);
        genderComboBox.setDisable(false);
    }

    /**
     * Метод для выгрузки информации о пользователе в специальные поля,
     * находящиеся на форме
     *
     * @param user           пользователь
     * @param firstName      поле отображающее имени пользователя
     * @param middleName     поле отображающее отчество
     * @param lastName       поле отображающее фамилию
     * @param userName       поле отображающее логин
     * @param password       поле отображающее пароль
     * @param birthDate      поле отображающее дату рождения
     * @param genderComboBox поле(комбобокс) отображающий пол
     */
    public void fillInformUser(User user, TextField firstName,
                               TextField middleName, TextField lastName,
                               TextField userName, PasswordField password,
                               DatePicker birthDate, ComboBox genderComboBox) {
        Fisher fisher = user.getFisher();
        lastName.setText(fisher.getLastName());
        userName.setText(user.getLogin());
        firstName.setText(fisher.getName());
        // поместить дату рождения в поле отображения даты, предварительно конвертировав
        // т.к. в базе она хранится в формате Date, а для отображение нужен формат LocalDate
        birthDate.setValue(ConvertionHelper.convertDataToLocalDate(fisher.getBirthDay()));
        middleName.setText(fisher.getMiddleName());
        password.setText(user.getPassword());
        if (fisher.getGender() == Gender.MAN) {
            genderComboBox.setValue("Мужской");
        } else {
            genderComboBox.setValue("Женский");
        }
    }

    /**
     * Метод для обновления информации о пользователе
     *
     * @param user           пользователь, информацию о котором нужно обновить
     * @param userName       поле Логин
     * @param password       поле Паролб
     * @param firstName      поле Имя
     * @param middleName     поле Отчество
     * @param lastName       Поле Фамилия
     * @param birthDate      поле Дата рождения
     * @param genderComboBox поле(комбо-бокс) Пол
     */
    public void updateInforUser(User user, TextField userName,
                                PasswordField password, TextField firstName,
                                TextField middleName, TextField lastName,
                                DatePicker birthDate, ComboBox genderComboBox) {
        // если хотябы одно поле не открыто для редактирования, значит
        // и все не открыты для редактирвоания
        if (lastName.isEditable()) {
            Fisher fisher = user.getFisher();
            Date birthDateConvert = ConvertionHelper.convertLocalDateToDate(birthDate.getValue());
            Gender gender = genderComboBox.getValue().equals("Мужской") ? Gender.MAN : Gender.WOMAN;
            // если ни одно из полей не изменено, то не редактируем
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
                // заменяем фамилию рыбака
                fisher.setLastName(lastName.getText());
                fisher.setBirthDay(birthDateConvert);
                fisher.setGender(gender);

                factory.getFisherDAO().updateFisher(fisher);
                // изменяем рыбака у пользователя
                user.setFisher(fisher);
                factory.getUserDAO().updateUser(user);

                lastName.setEditable(false);
                userName.setEditable(false);
                firstName.setEditable(false);
                birthDate.setDisable(true);
                // запрещаем редактирвоать поле Отчество
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
}
