package ru.seflet.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.seflet.chat.domain.Chat;
import ru.seflet.chat.domain.User;
import ru.seflet.chat.security.Guest;
import ru.seflet.chat.service.ChatService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class IndexPage extends Page {
    private final ChatService chatService;

    public IndexPage(ChatService chatService) {
        this.chatService = chatService;
    }

    @Guest
    @GetMapping({"", "/"})
    public String index(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        if (user != null) {
            List<Chat> chats = chatService.findAll().stream().filter(chat -> chat.getMembers().contains(user)).collect(Collectors.toList());
            model.addAttribute("chats", chats);
        }
        return "IndexPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        unsetUser(httpSession);
        return "redirect:/";
    }
}
