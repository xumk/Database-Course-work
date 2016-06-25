package modal.dbservice.dao;

import modal.entity.Lake;

import java.util.List;

/**
 * Интерфейс для работы с таблицей Наживки
 * создан для сокрытия реализации
 */
public interface LakeDAO {
    void addLake(Lake lake);

    void updateLake(Lake lake);

    Lake getLakeById(Long lakeId);

    List<Lake> getAllLake();

    void deleteLake(Lake lake);
}
