package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {

    private final UserRepository userDao;

    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public String userPage(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "users";
    }
}
