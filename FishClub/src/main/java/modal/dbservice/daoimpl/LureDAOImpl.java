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
 * DAO класс для работы с таблицей Наживок
 */
public class LureDAOImpl extends GeneralDAO implements LureDAO {

    /**
     * Конструктор класса
     *
     * @param sessionFactory объект для создания сессий
     */
    public LureDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод добавления наживки в базу
     *
     * @param lure наживка
     */
    public void addLure(Lure lure) {
        addObject(lure);
    }

    /**
     * Метод обновления информации о наживки в базе даннхы
     *
     * @param lure наживка
     */
    @Override
    public void updateLure(Lure lure) {
        updateObject(lure);
    }

    /**
     * Метод получения информации о наживки по идентификатору
     *
     * @param lureId идентификатор наживки
     * @return объектное представление записи о Наживки
     */
    @Override
    public Lure getLureById(Long lureId) {
        Session session = null;
        Lure lure = null;
        try {
            session = this.sessionFactory.openSession();
            lure = session.get(Lure.class, lureId);
            // инициализация всех списков-связей каждой наживки
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

    /**
     * Метод для инициализации всех списков-связей наживки
     *
     * @param lure наживка
     */
    private void initializeFisherCollections(Lure lure) {
        Hibernate.initialize(lure.getFishs());
        Hibernate.initialize(lure.getFishers());
    }

    /**
     * Метод получения всех наживок из базы данных
     *
     * @return список наживок
     */
    @Override
    public List<Lure> getAllLures() {
        Session session = null;
        List<Lure> lures = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            lures = session.createCriteria(Lure.class).list();
            // инициализация всех списков-связей каждой наживки
            lures.stream()
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

        return lures;
    }

    /**
     * Удаление наживки из базы данных
     *
     * @param lure
     */
    @Override
    public void deleteLure(Lure lure) {
        lure.setFishs(null);
        lure.setFishers(null);
        deleteObject(lure);
    }
}
