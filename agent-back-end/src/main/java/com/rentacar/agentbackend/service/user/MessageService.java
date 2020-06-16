package com.rentacar.agentbackend.service.user;

import com.rentacar.agentbackend.domain.Message;
import com.rentacar.agentbackend.domain.dto.MessageDTO;

import java.util.List;

public interface MessageService {

    Message createMessage(MessageDTO messageDTO);

    List<Message> getAllMessages();

    void deleteMessage(Long id);
}
