package com.rentacar.messageservice.service;

import com.rentacar.messageservice.domain.dto.MessageDTO;
import com.rentacar.messageservice.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean canUserSendMessage(MessageDTO messageDTO) {
        return true;
    }
}
