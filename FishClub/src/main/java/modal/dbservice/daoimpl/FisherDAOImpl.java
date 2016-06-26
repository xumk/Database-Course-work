package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import modal.dbservice.dao.FisherDAO;
import modal.entity.Fisher;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import view.AlertMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO класс для работы с таблицей рыбаков
 */
public class FisherDAOImpl extends GeneralDAO implements FisherDAO {

    /**
     * Конструктор класса
     *
     * @param sessionFactory объект для открытия сессий
     */
    public FisherDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод добавления рыбака в базу
     *
     * @param fisher рыбак
     */
    @Override
    public void addFisher(Fisher fisher) {
        addObject(fisher);
    }

    /**
     * Метод обновления рыбак
     *
     * @param fisher рыбак
     */
    @Override
    public void updateFisher(Fisher fisher) {
        updateObject(fisher);
    }

    /**
     * Метод получения рыбака по идентификатору
     *
     * @param id идентификатор
     * @return рыбак
     */
    @Override
    public Fisher getFisherById(Long id) {
        Session session = null;
        Fisher fisher = null;
        try {
            session = this.sessionFactory.openSession();
            fisher = session.get(Fisher.class, id);
            // инициализация всех списков-связей рыбака
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

    /**
     * Метод инициализации списков-связей рыбака
     *
     * @param fisher рыбак
     */
    private void initializeFisherCollections(Fisher fisher) {
        Hibernate.initialize(fisher.getFishs());
        Hibernate.initialize(fisher.getLakes());
        Hibernate.initialize(fisher.getLures());
    }

    /**
     * Метод получени всех рыбаков из таблицы базы данных
     *
     * @return список рыбаков
     */
    @Override
    public List getAllFishers() {
        Session session = null;
        List<Fisher> fishers = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            fishers = session.createCriteria(Fisher.class).list();
            // инициализация у всех рыбаков их списков-связей
            fishers.stream()
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
        return fishers;
    }

    /**
     * Метод удаления рыбака
     *
     * @param fisher рыбак
     */
    @Override
    public void deleteFisher(Fisher fisher) {
        deleteObject(fisher);
    }
}
