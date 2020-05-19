package com.rentacar.userservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @GetMapping
    public void getAllUsers() {

    }

    @GetMapping("/{id}")
    public void getUser(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

    }

    @PutMapping("/suspend/{id}")
    public void suspendUserFromRenting(@PathVariable Long id) {

    }
}
