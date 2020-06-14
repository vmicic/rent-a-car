package com.rentacar.messageservice.repository;

import com.rentacar.messageservice.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
