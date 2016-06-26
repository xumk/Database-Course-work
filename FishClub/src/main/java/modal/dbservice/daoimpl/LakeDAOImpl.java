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
 * DAO класс для работы с таблицей Озера
 */
public class LakeDAOImpl extends GeneralDAO implements LakeDAO {

    /**
     * Конструктор класса
     *
     * @param sessionFactory объект для создания сессий
     */
    public LakeDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод добавления озера в базу данных
     *
     * @param lake озеро
     */
    @Override
    public void addLake(Lake lake) {
        addObject(lake);
    }

    /**
     * метод обновления озера в базе даннхы
     *
     * @param lake озеро
     */
    @Override
    public void updateLake(Lake lake) {
        updateObject(lake);
    }

    /**
     * Метод получения озера по идентификатору
     *
     * @param lakeId идентификатор озера
     * @return озеро
     */
    @Override
    public Lake getLakeById(Long lakeId) {
        Session session = null;
        Lake lake = null;
        try {
            session = this.sessionFactory.openSession();
            lake = session.get(Lake.class, lakeId);
            // инициализация всех списков-связей озеро
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

    /**
     * Метод получения информации о всех озерах из базы данных
     *
     * @return список озер
     */
    @Override
    public List<Lake> getAllLake() {
        Session session = null;
        List<Lake> lakes = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            lakes = session.createCriteria(Lake.class).list();
            // инициализация всех списков-связей каждого озера
            lakes.stream()
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
        return lakes;
    }

    /**
     * Метод инициализации списков-связей озера
     *
     * @param lake
     */
    private void initializeFisherCollections(Lake lake) {
        Hibernate.initialize(lake.getFishs());
        Hibernate.initialize(lake.getFishers());
    }

    /**
     * Мето удаления озера из базы данных
     *
     * @param lake озеро
     */
    @Override
    public void deleteLake(Lake lake) {
        lake.setFishs(null);
        lake.setFishers(null);
        deleteObject(lake);
    }
}
