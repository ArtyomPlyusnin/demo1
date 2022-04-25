package com.example.demo.service;


import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

     void addUser(User user);

     User getUserById(int id);

     User updateUser(User user);

     void removeUserById(int id);

     List<User> listUsers();

     UserDetails loadUserByUsername (String username) throws UsernameNotFoundException;
}
