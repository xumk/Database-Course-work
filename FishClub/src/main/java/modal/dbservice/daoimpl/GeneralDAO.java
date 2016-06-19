package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import view.AlertMessage;

/**
 * Created by Алексей on 15.06.2016.
 */
public abstract class GeneralDAO {
    protected SessionFactory sessionFactory;

    public GeneralDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addObject(Object object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.save(object);
            e.commit();
        } catch (Exception var7) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка при вставке" + object.toString(),
                    var7.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateObject(Object object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.update(object);
            e.commit();
        } catch (Exception var7) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка при обновлении",
                    var7.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteObject(Object object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.delete(object);
            e.commit();
        } catch (Exception var7) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка при удалении",
                    var7.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
