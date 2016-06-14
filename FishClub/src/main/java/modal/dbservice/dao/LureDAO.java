package modal.dbservice.dao;

import modal.entity.Lure;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public interface LureDAO {
    void addLure(Lure lure) throws SQLException;

    void updateLure(Lure lure) throws SQLException;

    Lure getLureById(Long lureId) throws SQLException;

    List<Lure> getAllLures() throws SQLException;

    void deleteLure(Lure lure) throws SQLException;
}
