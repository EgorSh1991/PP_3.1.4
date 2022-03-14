package com.shundeev.src.dao;

import com.shundeev.src.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role findRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return entityManager
                .createQuery("select r from Role r where r.roleName=:roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }
}