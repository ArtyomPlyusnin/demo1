package com.example.demo.service;


import com.example.demo.model.User;

import java.util.List;

public interface UserService {

     void addUser(User user);

     User getUserById(int id);

     User updateUser(User user);

     void removeUserById(int id);

     List<User> listUsers();
}
