package com.example.demo.dao;

import com.example.demo.model.Role;

import java.util.HashSet;
import java.util.List;

public interface RoleDAO {
    List<Role> index();

    HashSet<Role> getSetOfRoles(String[] roleNames);

    Role getRoleByName(String name);

    void add(Role role);

    void edit(Role role);

    Role getById(int id);
}