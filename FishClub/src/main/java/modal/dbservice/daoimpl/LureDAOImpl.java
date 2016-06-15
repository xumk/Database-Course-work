package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import modal.dbservice.dao.LureDAO;
import modal.entity.Lure;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import view.AlertMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public class LureDAOImpl extends GeneralDAO implements LureDAO {
    private SessionFactory sessionFactory;

    public LureDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public void addLure(Lure lure) {
        addObject(lure);
    }

    @Override
    public void updateLure(Lure lure) {
        updateObject(lure);
    }

    @Override
    public Lure getLureById(Long lureId) {
        Session session = null;
        Lure lure = null;
        try {
            session = this.sessionFactory.openSession();
            lure = session.load(Lure.class, lureId);
            this.initializeFisherCollections(lure);
        } catch (Exception var8) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения: " + lureId,
                    var8.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return lure;
    }

    private void initializeFisherCollections(Lure fish) {
        Hibernate.initialize(fish.getFishs());
        Hibernate.initialize(fish.getFishers());
    }

    public List<Lure> getAllLures() {
        Session session = null;
        List<Lure> lures = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            lures = session.createCriteria(Lure.class).list();
            lures.stream().forEach(this::initializeFisherCollections);
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

        return lures;
    }

    @Override
    public void deleteLure(Lure lure) {
        deleteObject(lure);
    }
}
