package com.example.demo.dao;

import com.example.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> index() {
        return em.createQuery("SELECT a FROM Role a", Role.class)
                .getResultList();

    }
    @Override
    public Role getRoleByName(String name) {
        return em.createQuery(
                "SELECT r from Role r where r.name=:name", Role.class
        ).setParameter("name", name).getSingleResult();
    }


    @Override
    public HashSet<Role> getSetOfRoles(String[] roleNames) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roleNames) {
            roleSet.add(getRoleByName(role));
        }
        return (HashSet) roleSet;
    }

    @Override
    public void add(Role role) {
        em.persist(role);
    }

    @Override
    public void edit(Role role) {
        em.merge(role);
    }

    @Override
    public Role getById(int id) {
        return em.find(Role.class, id);
    }
}
