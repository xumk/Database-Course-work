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

public class AvailabilityDAO extends GeneralDAO {

    public AvailabilityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Availability getAvailabilityByLureAndFisherId(Long fisherId, Long lureId) {
        Session session = null;
        Availability availability = null;
        try {
            session = this.sessionFactory.openSession();
            Criteria e = session.createCriteria(Availability.class);
            availability = (Availability) e.add(Restrictions.eq("fisherId", fisherId))
                    .add(Restrictions.eq("lureId", lureId))
                    .uniqueResult();
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

    public void removeAll(List<Availability> list) {
        list.stream().forEach(this::deleteObject);
    }

    public Availability getById(long id) {
        Session session = null;
        Availability availability = null;
        try {
            session = this.sessionFactory.openSession();
            availability = session.get(Availability.class, id);
            Hibernate.initialize(availability.getLure().getFishers());
            Hibernate.initialize(availability.getLure().getFishs());
            Hibernate.initialize(availability.getFisher().getLures());
            Hibernate.initialize(availability.getFisher().getLakes());
            Hibernate.initialize(availability.getFisher().getFishs());
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
}
