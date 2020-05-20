package com.rentacar.userservice.controller;

import com.rentacar.userservice.domain.dto.MessageDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @PostMapping
    public void createMessage(@RequestBody MessageDTO messageDTO) {

    }

    @GetMapping
    public void getAllMessages() {

    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {

    }
}
