package modal.dbservice.dao;

import modal.entity.Fish;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public interface FishDAO {
    void addFish(Fish fish) throws SQLException;

    void updateFish(Fish fish) throws SQLException;

    Fish getFishById(Long fishId) throws SQLException;

    List<Fish> getAllFishs() throws SQLException;

    void deleteFish(Fish fish) throws SQLException;
}
