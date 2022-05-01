package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }


    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }


    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }


    @Override
    public void removeUserById(int id) {
        userDao.removeUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.loadUserByUsername(username);
        if (user == null){
            throw  new UsernameNotFoundException(String.format("User '%s' не найден", username));
        }

        return user;
    }

}
