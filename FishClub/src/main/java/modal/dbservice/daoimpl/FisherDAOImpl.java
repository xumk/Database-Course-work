package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import modal.dbservice.dao.FisherDAO;
import modal.entity.Fisher;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import view.AlertMessage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 13.06.2016.
 */
public class FisherDAOImpl implements FisherDAO {
    private SessionFactory sessionFactory;

    public FisherDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addFisher(Fisher fisher) throws SQLException {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(fisher);
            transaction.commit();
        } catch (Exception var7) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка при вставке",
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
    public void updateFisher(Fisher fisher) throws SQLException {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.update(fisher);
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
    public Fisher getFisherById(Long id) throws SQLException {
        Session session = null;
        Fisher fisher = null;
        try {
            session = this.sessionFactory.openSession();
            fisher = session.load(Fisher.class, id);
            this.initializeFisherCollections(fisher);
        } catch (Exception var8) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения",
                    var8.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

        }
        return fisher;
    }

    private void initializeFisherCollections(Fisher fisher) {
        Hibernate.initialize(fisher.getFishs());
        Hibernate.initialize(fisher.getLakes());
        Hibernate.initialize(fisher.getLures());
    }

    @Override
    public List getAllFishers() throws SQLException {
        Session session = null;
        List<Fisher> fishers = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            fishers = session.createCriteria(Fisher.class).list();
            fishers.stream().forEach(this::initializeFisherCollections);
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
        return fishers;
    }

    @Override
    public void deleteFisher(Fisher fisher) throws SQLException {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.delete(fisher);
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
