package ru.kata.spring.boot_security.demo.configs.DAO;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    User getUserByName(String login);

    User getUserById(long id);
}
