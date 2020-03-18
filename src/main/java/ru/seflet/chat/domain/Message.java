package ru.seflet.chat.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty
    private String message;

    private long senderId;

    private String sendetLogin;

    @ManyToOne
    private Chat chat;

    @CreationTimestamp
    private Date creationTime;

    public Message() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getSendetLogin() {
        return sendetLogin;
    }

    public void setSendetLogin(String sendetLogin) {
        this.sendetLogin = sendetLogin;
    }
}
