package modal.dbservice.dao;

import modal.entity.Fish;

import java.util.List;

/**
 * Интерфейс для работы с таблицей Рыбы
 * создан для сокрытия реализации
 */
public interface FishDAO {
    void addFish(Fish fish);

    void updateFish(Fish fish);

    Fish getFishById(Long fishId);

    List<Fish> getAllFishs();

    void deleteFish(Fish fish);
}
