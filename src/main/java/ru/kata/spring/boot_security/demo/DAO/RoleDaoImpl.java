package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findById(long role_id){
        return entityManager.find(Role.class, role_id);
    }
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }

    public void addRole(Role role) {
        entityManager.persist(role);
    }

    public Role getRole(String userRole) {
        return entityManager.createQuery("select r from Role r where r.userRole =: userRole", Role.class)
                .setParameter("userRole", userRole).getSingleResult();
    }
}
