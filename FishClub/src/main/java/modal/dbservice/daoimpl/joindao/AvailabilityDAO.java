package modal.dbservice.daoimpl.joindao;

import javafx.scene.control.Alert;
import modal.dbservice.daoimpl.GeneralDAO;
import modal.entity.joinentity.Availability;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import view.AlertMessage;

import java.util.List;

/**
 * DAO класс для работы с таблицей Налицие базы данных
 */
public class AvailabilityDAO extends GeneralDAO {

    /**
     * Конструктор класса
     *
     * @param sessionFactory объект создания сессий
     */
    public AvailabilityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Метод получения записи о налиции наживки у рыбака
     * из базы данных по идентификаторам рыбака и наживки
     *
     * @param fisherId идентификатор рыбака
     * @param lureId   идентификатор наживки
     * @return наличие
     */
    public Availability getAvailabilityByLureAndFisherId(Long fisherId, Long lureId) {
        Session session = null;
        Availability availability = null;
        try {
            session = this.sessionFactory.openSession();
            Criteria e = session.createCriteria(Availability.class);
            // создаем запрос к баззе данных по двум ограничем по ID рыбака и наживки
            availability = (Availability) e.add(Restrictions.eq("fisherId", fisherId))
                    .add(Restrictions.eq("lureId", lureId))
                    .uniqueResult();
            initializeAvailability(availability);
        } catch (Exception exaption) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения отношения 'проживает' по ИД: " + fisherId + lureId,
                    exaption.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return availability;
    }

    /**
     * Метод удаления всех записей
     *
     * @param list список записей, которые нужно удалить
     */
    public void removeAll(List<Availability> list) {
        list.stream().forEach(this::deleteObject);
    }

    /**
     * Мето получения всех записей из таблице наличия по идентификатору
     *
     * @param id идентификатор
     * @return список наличий
     */
    public Availability getById(long id) {
        Session session = null;
        Availability availability = null;
        try {
            session = this.sessionFactory.openSession();
            availability = session.get(Availability.class, id);
            // инициализация списков-связей
            initializeAvailability(availability);
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
        return availability;
    }

    /**
     * Метод для инициализации списков таблицы Наличия
     *
     * @param availability наличие
     */
    private void initializeAvailability(Availability availability) {
        Hibernate.initialize(availability.getLure().getFishers());
        Hibernate.initialize(availability.getLure().getFishs());
        Hibernate.initialize(availability.getFisher().getLures());
        Hibernate.initialize(availability.getFisher().getLakes());
        Hibernate.initialize(availability.getFisher().getFishs());
    }
}
