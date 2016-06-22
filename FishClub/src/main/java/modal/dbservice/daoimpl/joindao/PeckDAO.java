package modal.dbservice.daoimpl.joindao;

import modal.dbservice.daoimpl.GeneralDAO;
import modal.entity.joinentity.Peck;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Алексей on 20.06.2016.
 */
public class PeckDAO extends GeneralDAO {
    public PeckDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void removeAll(List<Peck> pecks) {
        pecks.stream().forEach(this::deleteObject);
    }
}
