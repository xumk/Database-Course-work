package modal.dbservice.dao;

import modal.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(User var1) throws SQLException;

    void updateUser(User var1) throws SQLException;

    User getUserById(Long var1) throws SQLException;

    User getUserByLogin(String var1);

    List<User> getAllUsers() throws SQLException;

    void deleteUsers(User var1) throws SQLException;
}

