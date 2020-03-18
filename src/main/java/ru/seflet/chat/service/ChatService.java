package ru.seflet.chat.service;

import org.springframework.stereotype.Service;
import ru.seflet.chat.domain.Chat;
import ru.seflet.chat.domain.User;
import ru.seflet.chat.form.ChatCredentials;
import ru.seflet.chat.repository.ChatRepository;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;

    public ChatService(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    public Chat findById(Long id) {
        return id == null ? null : chatRepository.findById(id).orElse(null);
    }

    public Chat createChat(ChatCredentials chatCredentials) {
        Chat chat = new Chat();
        chat.setTitle(chatCredentials.getTitle());
        long adminId = chatCredentials.getAdminId();
        chat.setAdminId(adminId);
        User admin = userService.findById(adminId);
        chat.getMembers().add(admin);
        return chatRepository.save(chat);
    }

    public void deleteChat(Chat chat) {
        chatRepository.delete(chat);
    }
}
