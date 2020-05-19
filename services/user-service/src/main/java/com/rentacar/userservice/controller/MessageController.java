package com.rentacar.userservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @PostMapping
    public void createMessage() {

    }

    @GetMapping
    public void getAllMessages() {

    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {

    }
}
