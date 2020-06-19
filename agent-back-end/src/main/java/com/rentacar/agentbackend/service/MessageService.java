package com.rentacar.agentbackend.service;

import com.rentacar.agentbackend.domain.Message;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.MessageDTO;

import java.util.List;

public interface MessageService {

    Message createMessage(MessageDTO messageDTO, User loggedInUser);

    List<Message> getAllMessages();
    
    boolean canUserSendMessage(Long id, User loggedInUser);

    void deleteMessage(Long id);
}
