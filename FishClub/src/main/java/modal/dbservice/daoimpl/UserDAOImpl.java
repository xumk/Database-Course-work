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

public class UserDAOImpl extends GeneralDAO implements UserDAO {

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        addObject(user);
    }

    @Override
    public void updateUser(User user){
        updateObject(user);
    }

    @Override
    public User getUserById(Long userId) {
        Session session = null;
        User user = null;
        try {
            session = this.sessionFactory.openSession();
            user = session.get(User.class, userId);
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

    private void initializeFisherCollections(User user) {
        Hibernate.initialize(user.getFisher().getFishs());
        Hibernate.initialize(user.getFisher().getLakes());
        Hibernate.initialize(user.getFisher().getLures());
    }

    @Override
    public List<User> getAllUsers() {
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
    public void deleteUsers(User user) {
        deleteObject(user);
    }
}