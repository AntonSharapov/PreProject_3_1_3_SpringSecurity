package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    Role findById(long role_id);
    public Role getRole(String userRole);
    public void addRole(Role role);
    public List<Role> getAllRoles();
}
