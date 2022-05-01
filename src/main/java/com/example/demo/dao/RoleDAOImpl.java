package com.example.demo.dao;

import com.example.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> index() {
        List<Role> roles = em.createQuery("SELECT a FROM Role a", Role.class)
                .getResultList();
        return roles;
    }
}
