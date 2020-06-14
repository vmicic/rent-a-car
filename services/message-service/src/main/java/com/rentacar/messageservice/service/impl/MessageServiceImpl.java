package com.rentacar.messageservice.service.impl;

import com.rentacar.messageservice.client.AdvertisementServiceClient;
import com.rentacar.messageservice.client.UserServiceClient;
import com.rentacar.messageservice.domain.Message;
import com.rentacar.messageservice.domain.dto.MessageDTO;
import com.rentacar.messageservice.repository.MessageRepository;
import com.rentacar.messageservice.service.MessageService;
import com.rentacar.messageservice.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final AdvertisementServiceClient advertisementServiceClient;
    private final UserServiceClient userServiceClient;
    private final UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository, AdvertisementServiceClient advertisementServiceClient, UserServiceClient userServiceClient, UserService userService) {
        this.messageRepository = messageRepository;
        this.advertisementServiceClient = advertisementServiceClient;
        this.userServiceClient = userServiceClient;
        this.userService = userService;
    }

    @Override
    public boolean canUserSendMessage(Long id) {
        return this.advertisementServiceClient.canUsersExchangeMessages(id);
    }

    @Override
    public Message createMessage(MessageDTO messageDTO) {
        Message message = new Message();

        message.setContent(messageDTO.getContent());
        message.setLocalDateTime(LocalDateTime.now());
        message.setSender(userServiceClient.getLoggedInUser());
        message.setReceiver(userService.findById(messageDTO.getReceiverId()));

        return this.messageRepository.save(message);
    }
}
