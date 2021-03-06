package com.rentacar.messageservice.service.impl;

import com.rentacar.messageservice.client.AdvertisementServiceClient;
import com.rentacar.messageservice.client.UserServiceClient;
import com.rentacar.messageservice.domain.Message;
import com.rentacar.messageservice.domain.User;
import com.rentacar.messageservice.domain.dto.MessageDTO;
import com.rentacar.messageservice.repository.MessageRepository;
import com.rentacar.messageservice.service.MessageService;
import com.rentacar.messageservice.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public boolean canUserSendMessage(String email) {
        return this.advertisementServiceClient.canUsersExchangeMessages(email);
    }

    @Override
    public Message createMessage(MessageDTO messageDTO) {
        Message message = new Message();

        message.setContent(messageDTO.getContent());
        message.setLocalDateTime(LocalDateTime.now());
        message.setSender(userServiceClient.getLoggedInUser());
        message.setReceiver(userService.findByEmail(messageDTO.getUsernameReceiver()));

        return this.messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        User user = userServiceClient.getLoggedInUser();

        return this.messageRepository.findAllBySenderEqualsOrReceiverEqualsOrderByLocalDateTime(user, user);
    }
}
