package com.example.demo.dao;


import com.example.demo.model.User;

import java.util.List;

public interface UserDao {

     void addUser(User user);

     User getUserById(int id);

     User updateUser(User user);

     void removeUserById(int id);

     List<User> listUsers();

     User findByUsername(String username);
}
