package modal.dbservice.daoimpl.joindao;

import javafx.scene.control.Alert;
import modal.dbservice.daoimpl.GeneralDAO;
import modal.entity.joinentity.Distance;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import view.AlertMessage;

public class DistanceDAO extends GeneralDAO {

    public DistanceDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Distance getDistanceByLakeAndFisherId(Long fisherId, Long lakeId) {
        Session session = null;
        Distance distance = null;
        try {
            session = this.sessionFactory.openSession();
            Criteria e = session.createCriteria(Distance.class);
            distance = (Distance) e.add(Restrictions.eq("fisherId", fisherId))
                    .add(Restrictions.eq("lakeId", lakeId))
                    .uniqueResult();
        } catch (Exception exaption) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения отношения 'проживает' по ИД: " + fisherId + lakeId,
                    exaption.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return distance;
    }
}
