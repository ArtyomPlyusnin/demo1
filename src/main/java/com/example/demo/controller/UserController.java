package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String printUsers(ModelMap model) {
        model.addAttribute(userService.listUsers());
        return "user";
    }

    @PostMapping
    public String add(@ModelAttribute("user") User user,
                      @RequestParam("name") String name,
                      @RequestParam("lastName") String lastName,
                      @RequestParam("mail") String mail) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,  @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
