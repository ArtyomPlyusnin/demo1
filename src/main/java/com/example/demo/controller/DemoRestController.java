package com.example.demo.controller;

import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    private final UserService userService;
    private final RoleService roleService;

    private final UserDao userDao;
    private final RoleDAO roleDAO;

    public DemoRestController(UserService userService, RoleService roleService, UserDao userDao, RoleDAO roleDAO) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDao = userDao;
        this.roleDAO = roleDAO;
    }

    @GetMapping("/users")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        System.out.println(user);
        userService.addUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        userService.removeUserById(id);
        return "Юзер по ID" + id + " " + "удален";
    }
}