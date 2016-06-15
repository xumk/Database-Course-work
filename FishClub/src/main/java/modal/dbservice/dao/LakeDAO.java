package modal.dbservice.dao;

import modal.entity.Lake;

import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public interface LakeDAO {
    void addLake(Lake lake);

    void updateLake(Lake lake);

    Lake getLakeById(Long lakeId);

    List<Lake> getAllLake();

    void deleteLake(Lake lake);
}
