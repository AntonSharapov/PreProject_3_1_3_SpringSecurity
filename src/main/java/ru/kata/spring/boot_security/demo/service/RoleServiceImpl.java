package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleDaoImpl roleDao;

    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role findById(long role_id) {
        return roleDao.findById(role_id);
    }
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
    public Role getRole(String userRole) {
        return roleDao.getRole(userRole);
    }
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
}

