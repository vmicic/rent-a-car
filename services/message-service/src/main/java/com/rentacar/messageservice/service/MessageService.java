package com.rentacar.messageservice.service;

import com.rentacar.messageservice.domain.Message;
import com.rentacar.messageservice.domain.dto.MessageDTO;

public interface MessageService {

    boolean canUserSendMessage(String email);

    Message createMessage(MessageDTO messageDTO);
}
