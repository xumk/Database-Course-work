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
 * Класс DAO дря работы с таблицей Рыбы
 */
public class FishDAOImpl extends GeneralDAO implements FishDAO {

    /**
     * Конструктор класс, где производим всю инициализацию
     *
     * @param sessionFactory объект для создания сессий
     */
    public FishDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод добавления рыбы
     *
     * @param fish рыба, которую следует добавить
     */
    @Override
    public void addFish(Fish fish) {
        addObject(fish);
    }

    /**
     * Метод обновления рыбы
     *
     * @param fish рыба, которую нужно обновить
     */
    @Override
    public void updateFish(Fish fish) {
        updateObject(fish);
    }

    /**
     * Метод получения рыбы по идентификатору
     *
     * @param fishId идентификатор рыбы
     * @return запись из базы данных в объектном виде
     */
    @Override
    public Fish getFishById(Long fishId) {
        Session session = null;
        Fish fish = null;
        try {
            session = this.sessionFactory.openSession();
            fish = session.get(Fish.class, fishId);
            // инициализируем все коллекции (связи) существующие у данной рыбы
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

    /**
     * Метод инициализации коллекций у рыбы
     *
     * @param fish рыба
     */
    private void initializeFisherCollections(Fish fish) {
        Hibernate.initialize(fish.getLures());
        Hibernate.initialize(fish.getLakes());
        Hibernate.initialize(fish.getFishers());
    }

    /**
     * Метод получения списка всех рыб
     *
     * @return список всех рыб
     */
    public List<Fish> getAllFishs() {
        Session session = null;
        List<Fish> fishs = new ArrayList<>();
        try {
            session = this.sessionFactory.openSession();
            fishs = session.createCriteria(Fish.class).list();
            // производим инициализацию коллекций у всех рыб
            fishs.stream()
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

        return fishs;
    }

    /**
     * Метод удаления рыбы из базы данных
     * для начала приводим все связи к null
     * для простоты удаления
     *
     * @param fish
     */
    @Override
    public void deleteFish(Fish fish) {
        fish.setLakes(null);
        fish.setLures(null);
        fish.setFishers(null);
        deleteObject(fish);
    }
}
