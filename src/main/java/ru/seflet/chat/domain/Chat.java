package ru.seflet.chat.domain;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
public class Chat {
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    @NotNull
    @Size(max = 24)
    private String title;

    private long adminId;

    @UpdateTimestamp
    private Date updateTime;

    @ManyToMany
    private List<User> members;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    @OrderBy("creationTime desc")
    private List<Message> messages;

    public Chat() {
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
