package modal.dbservice.dao;

import modal.entity.Fisher;

import java.util.List;

/**
 * Created by Алексей on 13.06.2016.
 */
public interface FisherDAO {
    void addFisher(Fisher fisher);

    void updateFisher(Fisher fisher);

    Fisher getFisherById(Long id);

    List getAllFishers();

    void deleteFisher(Fisher fisher);
}
