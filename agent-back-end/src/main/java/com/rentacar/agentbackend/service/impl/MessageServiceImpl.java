package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.Message;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.MessageDTO;
import com.rentacar.agentbackend.repository.MessageRepository;
import com.rentacar.agentbackend.service.MessageService;
import com.rentacar.agentbackend.service.ReservationService;
import com.rentacar.agentbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ReservationService reservationService;

    public MessageServiceImpl(MessageRepository messageRepository, UserService userService, ReservationService reservationService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @Override
    public boolean canUserSendMessage(Long id, User loggedInUser) {
        return reservationService.canUsersExchangeMessages(id, loggedInUser);
    }

    @Override
    public Message createMessage(MessageDTO messageDTO, User loggedInUser) {
        Message message = new Message();

        message.setContent(messageDTO.getContent());
        message.setLocalDateTime(LocalDateTime.now());
        message.setSender(loggedInUser);
        message.setReceiver(userService.findById(messageDTO.getReceiverId()));

        return this.messageRepository.save(message);
    }

	@Override
	public List<Message> getAllMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMessage(Long id) {
		// TODO Auto-generated method stub
		
	}
}
