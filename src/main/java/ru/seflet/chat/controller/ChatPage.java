package ru.seflet.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.seflet.chat.domain.Chat;
import ru.seflet.chat.domain.User;
import ru.seflet.chat.form.MessageCredentials;
import ru.seflet.chat.form.UserCredentials;
import ru.seflet.chat.form.validator.MessageCredentialsValidator;
import ru.seflet.chat.service.ChatService;
import ru.seflet.chat.service.MessageService;
import ru.seflet.chat.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ChatPage extends Page {
    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;
    private final MessageCredentialsValidator messageCredentialsValidator;

    public ChatPage(ChatService chatService, MessageService messageService, UserService userService, MessageCredentialsValidator messageCredentialsValidator) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.userService = userService;
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
        model.addAttribute("userForm", new UserCredentials());
        return "ChatPage";
    }

    @PostMapping("chat/{id}/addUser")
    public String addUser(HttpSession httpSession, @PathVariable("id") Long id, @Valid @ModelAttribute UserCredentials userCredentials) {
        Chat chat = chatService.findById(id);
        User admin = getUser(httpSession);
        if (chat.getAdminId() != admin.getId()) {
            putMessage(httpSession, "You are not admin!");
            return "redirect:/chat/" + id;
        }
        User user = userService.findByLogin(userCredentials.getLogin());
        if (user == null) {
            putMessage(httpSession, "Can't find user.");
            return "redirect:/chat/" + id;
        }
        chat.getMembers().add(user);
        user.getChats().add(chat);
        userService.updateUser(user);
        return "redirect:/chat/" + id;
    }

    @PostMapping("chat/{id}")
    public String sendMessage(HttpSession httpSession, @Valid @ModelAttribute MessageCredentials chatMessage,
                              BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "redirect:/chat/" + id;
        }
        User user = getUser(httpSession);
        chatMessage.setChatId(id);
        chatMessage.setUserId(user.getId());
        chatMessage.setUserLogin(user.getLogin());
        messageService.sendMessage(chatMessage);
        return "redirect:/chat/" + id;
    }

    /**
     * TODO
     * На самом деле нужен DeleteMapping, но для html нет формы метода DELETE,
     */
    @PostMapping("chat/{id}/delete")
    public String deleteChat(HttpSession httpSession, @PathVariable("id") Long id) {
        Chat chat = chatService.findById(id);
        User user = getUser(httpSession);
        if (chat.getAdminId() != user.getId()) {
            putMessage(httpSession, "Only admin can delete chat!");
            return "redirect:/chat/" + id;
        }
        chatService.deleteChat(chat);
        putMessage(httpSession, "Chat successful deleted!");
        return "redirect:/";
    }

}
