package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String printUsers(Model model) {
        model.addAttribute("users",userService.listUsers());
        return "index";
    }


//    @GetMapping("/user")
//    public String getUser(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("roles", userService.getUserByEmail(user.getUsername()).getRoles());
//        model.addAttribute("user", userService.getUserByEmail(user.getUsername()));
//        return "index";
//    }



    @PostMapping
    public String add(@ModelAttribute("user") User user,
                      @RequestParam("name") String name,
                      @RequestParam("lastName") String lastName,
                      @RequestParam("mail") String mail) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
        public String crud(Model model) {
        model.addAttribute("listUsers",userService.listUsers());
            return "admin";
        }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.getUserById(user.getId()));
        return "user";
    }


    @GetMapping("/{id}/remove")
    public String remove(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute(userService.listUsers());
        return "index";}

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,  @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
