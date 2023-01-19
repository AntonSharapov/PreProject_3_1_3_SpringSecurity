package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.DAO.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;


public class RoleServiceImpl implements RoleService{
    private final RoleDaoImpl roleDao;

    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role findById(long role_id) {
        return roleDao.findById(role_id);
    }
}

