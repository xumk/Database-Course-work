package modal.dbservice.dao;

import modal.entity.Fisher;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 13.06.2016.
 */
public interface FisherDAO {
    void addFisher(Fisher fisher) throws SQLException;

    void updateFisher(Fisher fisher) throws SQLException;

    Fisher getFisherById(Long id) throws SQLException;

    List getAllFishers() throws SQLException;

    void deleteFisher(Fisher fisher) throws SQLException;
}
