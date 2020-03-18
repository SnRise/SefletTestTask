package ru.seflet.chat.service;

import org.springframework.stereotype.Service;
import ru.seflet.chat.domain.Chat;
import ru.seflet.chat.domain.Message;
import ru.seflet.chat.form.MessageCredentials;
import ru.seflet.chat.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;

    public MessageService(MessageRepository messageRepository, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
    }

    public Message sendMessage(MessageCredentials messageCredentials) {
        Message message = new Message();
        message.setMessage(messageCredentials.getMessage());
        message.setSenderId(messageCredentials.getUserId());
        message.setSendetLogin(messageCredentials.getUserLogin());
        Chat chat = chatService.findById(messageCredentials.getChatId());
        message.setChat(chat);
        messageRepository.save(message);
        chat.getMessages().add(message);
        return message;
    }
}
