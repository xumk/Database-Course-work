// так как это был пробный образец, не используемый ныне
// я комментирую его, оставив лишь на всякий случай
/*package modal.dbservice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DBWorker {
    private SessionFactory sessionFactory;
    private Session session;
    private static DBWorker worker;

    public static synchronized DBWorker instance(SessionFactory sessionFactory) {
        if(worker == null) {
            worker = new DBWorker(sessionFactory);
        }

        return worker;
    }

    private DBWorker(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = this.sessionFactory.openSession();
    }

    public <T> T getObjectById(Class<T> objectClass, long id) {
        return this.session.get(objectClass, Long.valueOf(id));
    }

    public <T> List<T> getObjectList(Class<T> objectClass) {
        return this.session.createCriteria(objectClass).list();
    }

    public <T> Long addObject(T object) {
        return (Long)this.session.save(object);
    }

    public void commit() {
        Transaction transaction = this.session.beginTransaction();
        transaction.commit();
    }

    public void sessionClose() {
        this.session.close();
    }
}*/

