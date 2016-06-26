package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import modal.dbservice.dao.UserDAO;
import modal.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import view.AlertMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO класс для работы с таблицей пользователей
 */
public class UserDAOImpl extends GeneralDAO implements UserDAO {

    /**
     * Конструктор класса
     *
     * @param sessionFactory объект для создания сессий
     */
    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод добавления пользователя в базу данных
     *
     * @param user пользователь
     */
    @Override
    public void addUser(User user) {
        addObject(user);
    }

    /**
     * Метод обновления информации о пользователе
     *
     * @param user пользователь
     */
    @Override
    public void updateUser(User user) {
        updateObject(user);
    }

    /**
     * Метод получения данных о пользователе по идентификатору
     *
     * @param userId идентификатор
     * @return пользователь
     */
    @Override
    public User getUserById(Long userId) {
        Session session = null;
        User user = null;
        try {
            session = this.sessionFactory.openSession();
            user = session.get(User.class, userId);
            // инициализация списков -связей у связаной
            // с пользователем сущности Рыбак
            this.initializeFisherCollections(user);
        } catch (Exception var8) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения: " + userId,
                    var8.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    /**
     * Метод получения пользователя по логину
     *
     * @param login логин пользователя
     * @return пользователь
     */
    @Override
    public User getUserByLogin(String login) {
        Session session = null;
        User user = null;
        try {
            session = this.sessionFactory.openSession();
            Criteria e = session.createCriteria(User.class);
            // получение записи из базы данных по логину пользователя
            user = (User) e.add(Restrictions.eq("login", login)).uniqueResult();
            if (user != null) {
                this.initializeFisherCollections(user);
            }
        } catch (Exception var8) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения юзера по логину: " + login,
                    var8.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

        }
        return user;
    }

    /**
     * Метод для инициализации всех списков-связей
     * в связанной с пользователем сущности Рыбак
     *
     * @param user пользователь
     */
    private void initializeFisherCollections(User user) {
        Hibernate.initialize(user.getFisher().getFishs());
        Hibernate.initialize(user.getFisher().getLakes());
        Hibernate.initialize(user.getFisher().getLures());
    }

    /**
     * Получить список всех пользователей хранящихся в базе данных
     *
     * @return список пользователей
     */
    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> users = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            users = session.createCriteria(User.class).list();
            // проинициализировать все списки-связи
            users.stream()
                    .forEach(this::initializeFisherCollections);
        } catch (Exception var7) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка при получении всех пользователей",
                    var7.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    /**
     * Удаление пользователя из базы
     * @param user пользователь
     */
    @Override
    public void deleteUsers(User user) {
        deleteObject(user);
    }
}