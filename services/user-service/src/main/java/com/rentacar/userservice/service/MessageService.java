package com.rentacar.userservice.service;

import com.rentacar.userservice.domain.Message;
import com.rentacar.userservice.domain.dto.MessageDTO;

import java.util.List;

public interface MessageService {

    Message createMessage(MessageDTO messageDTO);

    List<Message> getAllMessages();

    void deleteMessage(Long id);
}
