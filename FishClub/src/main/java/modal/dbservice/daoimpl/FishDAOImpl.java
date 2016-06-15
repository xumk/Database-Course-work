package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import modal.dbservice.dao.FishDAO;
import modal.entity.Fish;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import view.AlertMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public class FishDAOImpl extends GeneralDAO implements FishDAO {
    private SessionFactory sessionFactory;

    public FishDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addFish(Fish fish) {
        addObject(fish);
    }

    @Override
    public void updateFish(Fish fish) {
        updateObject(fish);
    }

    @Override
    public Fish getFishById(Long fishId) {
        Session session = null;
        Fish fish = null;
        try {
            session = this.sessionFactory.openSession();
            fish = session.load(Fish.class, fishId);
            this.initializeFisherCollections(fish);
        } catch (Exception var8) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения: " + fishId,
                    var8.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return fish;
    }

    private void initializeFisherCollections(Fish fish) {
        Hibernate.initialize(fish.getLures());
        Hibernate.initialize(fish.getLakes());
        Hibernate.initialize(fish.getFishers());
    }

    public List<Fish> getAllFishs() {
        Session session = null;
        List<Fish> fishs = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            fishs = session.createCriteria(Fish.class).list();
            fishs.stream().forEach(this::initializeFisherCollections);
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

        return fishs;
    }

    @Override
    public void deleteFish(Fish fish) {
        deleteObject(fish);
    }
}
