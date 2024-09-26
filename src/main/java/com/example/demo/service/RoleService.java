package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.Collection;
import java.util.HashSet;

public interface RoleService {

    Collection<Role> index();

    Role getRoleByName(String name);

    HashSet<Role> getSetOfRoles(String[] roleNames);

    void add(Role role);

    void edit(Role role);

    Role getById(int id);
}
