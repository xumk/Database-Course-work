package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import modal.dbservice.dao.LakeDAO;
import modal.entity.Lake;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import view.AlertMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public class LakeDAOImpl extends GeneralDAO implements LakeDAO {

    public LakeDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addLake(Lake lake) {
        addObject(lake);
    }

    @Override
    public void updateLake(Lake lake) {
        updateObject(lake);
    }

    @Override
    public Lake getLakeById(Long lakeId) {
        Session session = null;
        Lake lake = null;
        try {
            session = this.sessionFactory.openSession();
            lake = session.load(Lake.class, lakeId);
            this.initializeFisherCollections(lake);
        } catch (Exception var8) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения: " + lakeId,
                    var8.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return lake;
    }

    @Override
    public List<Lake> getAllLake() {
        Session session = null;
        List<Lake> lakes = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            lakes = session.createCriteria(Lake.class).list();
            lakes.stream().forEach(this::initializeFisherCollections);
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
        return lakes;
    }

    private void initializeFisherCollections(Lake lake) {
        Hibernate.initialize(lake.getFishs());
        Hibernate.initialize(lake.getFishers());
    }
    @Override
    public void deleteLake(Lake lake) {
        deleteObject(lake);
    }
}
