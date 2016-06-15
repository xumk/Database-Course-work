package modal.dbservice.dao;

import modal.entity.Fish;

import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public interface FishDAO {
    void addFish(Fish fish);

    void updateFish(Fish fish);

    Fish getFishById(Long fishId);

    List<Fish> getAllFishs();

    void deleteFish(Fish fish);
}
