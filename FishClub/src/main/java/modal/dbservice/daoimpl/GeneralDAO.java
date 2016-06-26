package modal.dbservice.daoimpl;

import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import view.AlertMessage;

/**
 * Абстрактный класс DAO, куда вынесены все типичные функции
 * для работы с сущностями базы данных
 */
public abstract class GeneralDAO {
    protected SessionFactory sessionFactory;

    public GeneralDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод добавление объекта (записи) в базу данных
     *
     * @param object запись, которую следует добавить
     */
    public void addObject(Object object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.save(object);
            // заносим изменениния в базу
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

    /**
     * Метод обновления записи базы данных
     *
     * @param object запись которую следует обновить
     */
    public void updateObject(Object object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.update(object);
            // заносим изменения в базу
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

    /**
     * Метод удаления записи из базы данных
     *
     * @param object запись, которую следует удалить
     */
    public void deleteObject(Object object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.delete(object);
            // сохраняем изменения в базе
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
