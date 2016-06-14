package modal.dbservice.dao;

import modal.entity.Lake;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public interface LakeDAO {
    void addLake(Lake lake) throws SQLException;

    void updateLake(Lake lake) throws SQLException;

    Lake getLakeById(Long lakeId) throws SQLException;

    List<Lake> getAllLake() throws SQLException;

    void deleteLake(Lake lake) throws SQLException;
}
