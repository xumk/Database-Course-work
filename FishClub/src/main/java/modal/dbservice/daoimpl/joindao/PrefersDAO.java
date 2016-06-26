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
 * DAO класс для работы с таблицей-отношением Предпочитает
 */
public class PrefersDAO extends GeneralDAO {

    /**
     * Конструктор для создания экземпляра класса
     *
     * @param sessionFactory объект для создания сессий
     */
    public PrefersDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Удалить список всех связей
     *
     * @param list списко связей
     */
    public void removeAll(List<Prefers> list) {
        list.stream().forEach(this::deleteObject);
    }


    /**
     * Метод получения записи из таблицы по идентификатору
     *
     * @param id идентификатор
     * @return связь
     */
    public Prefers getById(long id) {
        Session session = null;
        Prefers prefers = null;
        try {
            session = this.sessionFactory.openSession();
            prefers = session.get(Prefers.class, id);
            // инициализируем все списки сущности
            initializePrefers(prefers);
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

    /**
     * Метод для инициализации всех списко сущности Предпочитает
     *
     * @param prefers объект-сущность
     */
    private void initializePrefers(Prefers prefers) {
        Hibernate.initialize(prefers.getFish().getFishers());
        Hibernate.initialize(prefers.getFish().getLakes());
        Hibernate.initialize(prefers.getFish().getLures());
        Hibernate.initialize(prefers.getFisher().getLures());
        Hibernate.initialize(prefers.getFisher().getLakes());
        Hibernate.initialize(prefers.getFisher().getFishs());
    }
}
