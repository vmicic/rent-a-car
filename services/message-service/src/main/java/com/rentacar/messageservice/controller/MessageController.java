package com.rentacar.messageservice.controller;

import com.rentacar.messageservice.domain.Message;
import com.rentacar.messageservice.domain.dto.MessageDTO;
import com.rentacar.messageservice.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO) {
        if(!messageService.canUserSendMessage(messageDTO.getUsernameReceiver())) {
            return new ResponseEntity<>("Cannot send message to requested user", HttpStatus.BAD_REQUEST);
        }

        Message message = this.messageService.createMessage(messageDTO);

        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

}
