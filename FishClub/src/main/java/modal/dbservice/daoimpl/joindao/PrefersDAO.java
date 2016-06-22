package modal.dbservice.daoimpl.joindao;

import javafx.scene.control.Alert;
import modal.dbservice.daoimpl.GeneralDAO;
import modal.entity.joinentity.Prefers;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import view.AlertMessage;

import java.util.List;

/**
 * Created by Алексей on 22.06.2016.
 */
public class PrefersDAO extends GeneralDAO {
    public PrefersDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void removeAll(List<Prefers> list) {
        list.stream().forEach(this::deleteObject);
    }

    public Prefers getById(long id) {
        Session session = null;
        Prefers prefers = null;
        try {
            session = this.sessionFactory.openSession();
            prefers = session.get(Prefers.class, id);
            Hibernate.initialize(prefers.getFish().getFishers());
            Hibernate.initialize(prefers.getFish().getLakes());
            Hibernate.initialize(prefers.getFish().getLures());
            Hibernate.initialize(prefers.getFisher().getLures());
            Hibernate.initialize(prefers.getFisher().getLakes());
            Hibernate.initialize(prefers.getFisher().getFishs());
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
        return prefers;
    }
}
