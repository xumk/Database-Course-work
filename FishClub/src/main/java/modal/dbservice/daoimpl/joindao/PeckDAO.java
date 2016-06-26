package modal.dbservice.daoimpl.joindao;

import modal.dbservice.daoimpl.GeneralDAO;
import modal.entity.joinentity.Peck;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * DAO класс для работы с таблицей-отношеним Клюёт
 */
public class PeckDAO extends GeneralDAO {

    /**
     * Конструктор класса
     *
     * @param sessionFactory объект для создания сессий
     */
    public PeckDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Метод для удаления информации о всех связях
     *
     * @param pecks список связей на удаление
     */
    public void removeAll(List<Peck> pecks) {
        pecks.stream().forEach(this::deleteObject);
    }
}
