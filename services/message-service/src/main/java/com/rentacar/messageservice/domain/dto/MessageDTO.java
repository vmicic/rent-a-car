package com.rentacar.messageservice.domain.dto;

public class MessageDTO {

    private String usernameReceiver;

    private String content;

    public MessageDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsernameReceiver() {
        return usernameReceiver;
    }

    public void setUsernameReceiver(String usernameReceiver) {
        this.usernameReceiver = usernameReceiver;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "username='" + usernameReceiver + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
