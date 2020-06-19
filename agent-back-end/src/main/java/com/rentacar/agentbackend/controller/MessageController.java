package com.rentacar.agentbackend.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.agentbackend.domain.dto.MessageDTO;
import com.rentacar.agentbackend.domain.Message;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.service.MessageService;
import com.rentacar.agentbackend.service.UserService;

@RestController
@RequestMapping("/messages")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
    
	@PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO, Principal user) {
		
		User loggedInUser = userService.findOneByEmail(user.getName());
		
        if(!messageService.canUserSendMessage(messageDTO.getReceiverId(), loggedInUser)) {
            return new ResponseEntity<>("Cannot send message to requested user", HttpStatus.BAD_REQUEST);
        }

        Message message = this.messageService.createMessage(messageDTO, loggedInUser);

        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    @GetMapping
    public void getAllMessages() {

    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {

    }
}
