package com.rentacar.userservice.domain.dto;

public class MessageDTO {

    private Long receiverId;

    private String content;

    public MessageDTO() {
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
