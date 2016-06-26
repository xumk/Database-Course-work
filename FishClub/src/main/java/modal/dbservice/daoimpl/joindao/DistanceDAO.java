package modal.dbservice.daoimpl.joindao;

import javafx.scene.control.Alert;
import modal.dbservice.daoimpl.GeneralDAO;
import modal.entity.joinentity.Distance;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import view.AlertMessage;

import java.util.List;

/**
 * DAO класс для работы с таблицей Расстояние
 */
public class DistanceDAO extends GeneralDAO {

    /**
     * Конструктор класса
     *
     * @param sessionFactory объект для создания сессии
     */
    public DistanceDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Метод получения информации о расстоянии между рыбаком и озером
     * из таблицы по идентификатором рыбак и озера
     *
     * @param fisherId идентификатор рыбака
     * @param lakeId   идентификатор озера
     * @return объект-записиь из БД Расстояние
     */
    public Distance getDistanceByLakeAndFisherId(Long fisherId, Long lakeId) {
        Session session = null;
        Distance distance = null;
        try {
            session = this.sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Distance.class);
            // создаем ограничение для обращения к базе по двуз идентификаторам
            distance = (Distance) criteria.add(Restrictions.eq("fisherId", fisherId))
                    .add(Restrictions.eq("lakeId", lakeId))
                    .uniqueResult();
            initializeDistanceList(distance);
        } catch (Exception e) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения отношения 'проживает' по ИД: " + fisherId + lakeId,
                    e.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return distance;
    }

    /**
     * Метод удаления всех расстояний
     *
     * @param list список расстояний
     */
    public void removeAll(List<Distance> list) {
        list.stream().forEach(this::deleteObject);
    }

    /**
     * Метод получения расстояние по идентификатору
     *
     * @param id идентификатор
     * @return расстояние
     */
    public Distance getById(long id) {
        Session session = null;
        Distance distance = null;
        try {
            session = this.sessionFactory.openSession();
            distance = session.get(Distance.class, id);
            // инициализация всех списко
            initializeDistanceList(distance);
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
        return distance;
    }

    /**
     * Метод инициализации всех списков-связей расстояния
     *
     * @param distance
     */
    private void initializeDistanceList(Distance distance) {
        Hibernate.initialize(distance.getLake().getFishers());
        Hibernate.initialize(distance.getLake().getFishs());
        Hibernate.initialize(distance.getFisher().getLures());
        Hibernate.initialize(distance.getFisher().getLakes());
        Hibernate.initialize(distance.getFisher().getFishs());
    }
}
