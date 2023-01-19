package ru.kata.spring.boot_security.demo.configs.DAO;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    Role findById(long role_id);
}
