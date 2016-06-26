package modal.dbservice.daoimpl.joindao;

import javafx.scene.control.Alert;
import modal.dbservice.daoimpl.GeneralDAO;
import modal.entity.joinentity.Lived;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import view.AlertMessage;

import java.util.List;

/**
 * DAO класс для работы с таблицей-отношением Проживает
 */
public class LivedDAO extends GeneralDAO {

    /**
     * Конструктор класса
     *
     * @param sessionFactory объект для создания сессий
     */
    public LivedDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Метод получения записи из таблицы по двум идентификатором
     *
     * @param fishId идентификатор рыбы
     * @param lakeId идентификатор озера
     * @return объект-сущность проживает
     */
    public Lived getLivedByFishAndLakeId(Long fishId, Long lakeId) {
        Session session = null;
        Lived lived = null;
        try {
            session = this.sessionFactory.openSession();
            Criteria e = session.createCriteria(Lived.class);
            // создаем ограничения по котором ищется запись в базе
            lived = (Lived) e.add(Restrictions.eq("fishId", fishId))
                    .add(Restrictions.eq("lakeId", lakeId))
                    .uniqueResult();
        } catch (Exception exaption) {
            new AlertMessage(
                    "Ошибка",
                    "Ошибка получения отношения 'проживает' по ИД: " + fishId + lakeId,
                    exaption.getMessage(),
                    Alert.AlertType.ERROR
            );
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

        }
        return lived;
    }

    /**
     * Удалить всех рыб
     *
     * @param fishs список рыб, для удаления
     */
    public void removeAll(List<Lived> fishs) {
        fishs.stream().forEach(this::deleteObject);
    }
}
