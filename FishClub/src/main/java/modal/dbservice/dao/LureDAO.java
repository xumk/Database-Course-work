package modal.dbservice.dao;

import modal.entity.Lure;

import java.util.List;

/**
 * Created by Алексей on 15.06.2016.
 */
public interface LureDAO {
    void addLure(Lure lure);

    void updateLure(Lure lure);

    Lure getLureById(Long lureId);

    List<Lure> getAllLures();

    void deleteLure(Lure lure);
}
