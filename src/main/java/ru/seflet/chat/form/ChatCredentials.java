package ru.seflet.chat.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ChatCredentials {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 24)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Expected Latin letters or digits")
    private String title;

    private long adminId;


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
}
