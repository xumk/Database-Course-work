package modal.dbservice.dao;

import modal.entity.Fisher;

import java.util.List;

/**
 * Интерфейс для работы с таблицей Рыбаки
 * создан для сокрытия реализации
 */
public interface FisherDAO {
    void addFisher(Fisher fisher);

    void updateFisher(Fisher fisher);

    Fisher getFisherById(Long id);

    List getAllFishers();

    void deleteFisher(Fisher fisher);
}
