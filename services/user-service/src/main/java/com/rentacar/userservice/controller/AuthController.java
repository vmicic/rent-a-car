package com.rentacar.userservice.controller;

import com.rentacar.userservice.domain.JwtAuthenticationRequest;
import com.rentacar.userservice.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public void login(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {

    }

    @RequestMapping("/register")
    public void register(@RequestBody UserDTO userDTO) {

    }
}
