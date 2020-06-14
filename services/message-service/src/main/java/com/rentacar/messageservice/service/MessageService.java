package com.rentacar.messageservice.service;

import com.rentacar.messageservice.domain.dto.MessageDTO;

public interface MessageService {

    boolean canUserSendMessage(MessageDTO messageDTO);
}
