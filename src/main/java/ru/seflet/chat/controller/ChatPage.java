package ru.seflet.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.seflet.chat.domain.Chat;
import ru.seflet.chat.domain.User;
import ru.seflet.chat.form.MessageCredentials;
import ru.seflet.chat.form.validator.MessageCredentialsValidator;
import ru.seflet.chat.service.ChatService;
import ru.seflet.chat.service.MessageService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ChatPage extends Page {
    private final ChatService chatService;
    private final MessageService messageService;
    private final MessageCredentialsValidator messageCredentialsValidator;

    public ChatPage(ChatService chatService, MessageService messageService, MessageCredentialsValidator messageCredentialsValidator) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.messageCredentialsValidator = messageCredentialsValidator;
    }

    @InitBinder("chatMessage")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(messageCredentialsValidator);
    }

    @GetMapping("/chat/{id}")
    public String chat(Model model, @PathVariable("id") Long id) {
        Chat chat = chatService.findById(id);
        model.addAttribute("chat", chat);
        model.addAttribute("chatMessage", new MessageCredentials());
        return "ChatPage";
    }

    @PostMapping("chat/{id}/addUsers")
    public String addUsers(HttpSession httpSession, @PathVariable("id") Long id, List<User> users) {
        Chat chat = chatService.findById(id);
        User admin = getUser(httpSession);
        if (chat.getAdminId() != admin.getId()) {
            putMessage(httpSession, "You are not admin!");
            return "redirect:/";
        }
        chat.getMembers().addAll(users);
        users.forEach(user -> user.getChats().add(chat));
        return "redirect:/";
    }

    @PostMapping("chat/{id}")
    public String sendMessage(HttpSession httpSession, @Valid @ModelAttribute MessageCredentials chatMessage,
                              BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "ChatPage";
        }
        User user = getUser(httpSession);
        chatMessage.setChatId(id);
        chatMessage.setUserId(user.getId());
        chatMessage.setUserLogin(user.getLogin());
        messageService.sendMessage(chatMessage);
        return "redirect:/chat/" + id;

    }
}
