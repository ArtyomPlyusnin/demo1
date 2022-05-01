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
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("users", userService.listUsers());
        return "index";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.getUserById(user.getId()));
        return "user";
    }


    @PostMapping
    public String add(@ModelAttribute("user") User user,
                      @RequestParam("name") String name,
                      @RequestParam("lastName") String lastName,
                      @RequestParam("mail") String mail,
                      @RequestParam("username") String userName,
                      @RequestParam("password") String password) {
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/admin")
        public String crud(Model model) {
        model.addAttribute("users",userService.listUsers());
            return "admin";
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
