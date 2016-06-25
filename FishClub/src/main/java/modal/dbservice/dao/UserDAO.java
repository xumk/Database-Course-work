package modal.dbservice.dao;

import modal.entity.User;

import java.util.List;

/**
 * Интерфейс для работы с таблицей Пользователи
 * создан для сокрытия реализации
 */
public interface UserDAO {
    void addUser(User var1);

    void updateUser(User var1);

    User getUserById(Long var1);

    User getUserByLogin(String var1);

    List<User> getAllUsers();

    void deleteUsers(User var1);
}

