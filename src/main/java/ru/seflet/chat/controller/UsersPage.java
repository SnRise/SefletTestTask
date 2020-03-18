package ru.seflet.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.seflet.chat.security.Guest;
import ru.seflet.chat.service.UserService;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @Guest
    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }
}
