package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void editUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    User getByUsername(String login);

    User getUserById(long id);
}

