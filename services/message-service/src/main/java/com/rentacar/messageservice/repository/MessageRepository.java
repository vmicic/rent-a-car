package com.rentacar.messageservice.repository;

import com.rentacar.messageservice.domain.Message;
import com.rentacar.messageservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    //get all messages from specific user ordered by date sent
    List<Message> findAllBySenderEqualsOrReceiverEqualsOrderByLocalDateTime(User user, User userCopy);
}
