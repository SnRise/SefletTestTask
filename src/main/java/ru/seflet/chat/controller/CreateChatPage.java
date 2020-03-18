package ru.seflet.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.seflet.chat.domain.User;
import ru.seflet.chat.form.ChatCredentials;
import ru.seflet.chat.service.ChatService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CreateChatPage extends Page {
    private final ChatService chatService;

    public CreateChatPage(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("chat/create")
    public String createGet(Model model) {
        model.addAttribute("createForm", new ChatCredentials());
        return "ChatCreatePage";
    }

    @PostMapping("chat/create")
    public String createPost(@Valid @ModelAttribute ChatCredentials chatCredentials, BindingResult bindingResult,
                             HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "ChatCreatePage";
        }
        User admin = getUser(httpSession);
        chatCredentials.setAdminId(admin.getId());
        long id = chatService.createChat(chatCredentials).getId();
        putMessage(httpSession, "Chat successful created!");
        return "redirect:/chat/" + id;

    }
}
