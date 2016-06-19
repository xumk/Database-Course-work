package modal.dbservice.daoimpl.joindao;

import modal.dbservice.daoimpl.GeneralDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Алексей on 20.06.2016.
 */
public class PeckDAO extends GeneralDAO {
    public PeckDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
