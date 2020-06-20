package com.rentacar.messageservice.service;

import com.rentacar.messageservice.domain.Message;
import com.rentacar.messageservice.domain.dto.MessageDTO;

import java.util.List;

public interface MessageService {

    boolean canUserSendMessage(String email);

    Message createMessage(MessageDTO messageDTO);

    List<Message> getAllMessages();
}
