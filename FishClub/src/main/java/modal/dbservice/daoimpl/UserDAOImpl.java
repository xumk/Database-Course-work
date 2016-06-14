package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import modal.dbservice.dao.UserDAO;
import modal.entity.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import view.AlertMessage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) throws SQLException {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.save(user);
            e.commit();
        } catch (Exception var7) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка при вставке" + user.toString(),
                    var7.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.update(user);
            e.commit();
        } catch (Exception var7) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка при обновлении",
                    var7.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public User getUserById(Long userId) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = this.sessionFactory.openSession();
            user = session.load(User.class, userId);
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

    @Override
    public User getUserByLogin(String login) {
        Session session = null;
        User user = null;
        try {
            session = this.sessionFactory.openSession();
            Criteria e = session.createCriteria(User.class);
            user = (User) e.add(Restrictions.eq("login", login)).uniqueResult();
            if ( user != null) {
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

    private void initializeFisherCollections(User user) {
        Hibernate.initialize(user.getFisherman().getFishs());
        Hibernate.initialize(user.getFisherman().getLakes());
        Hibernate.initialize(user.getFisherman().getLures());
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Session session = null;
        List<User> users = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            users = session.createCriteria(User.class).list();
            users.stream().forEach(this::initializeFisherCollections);
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

    @Override
    public void deleteUsers(User user) throws SQLException {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.delete(user);
            e.commit();
        } catch (Exception var7) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка при удалении",
                    var7.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}