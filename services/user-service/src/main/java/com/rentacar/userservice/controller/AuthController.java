package com.rentacar.userservice.controller;

import com.rentacar.userservice.domain.User;
import com.rentacar.userservice.domain.UserTokenState;
import com.rentacar.userservice.security.TokenUtils;
import com.rentacar.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.rentacar.userservice.domain.JwtAuthenticationRequest;
import com.rentacar.userservice.domain.dto.UserDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 80;

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, TokenUtils tokenUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {
        try{
            final Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getUsername(),
                    jwtAuthenticationRequest.getPassword()));


            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenUtils.generateToken(jwtAuthenticationRequest.getUsername());
            Long expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
        }catch (BadCredentialsException e) {
            e.printStackTrace();
            return new ResponseEntity<UserTokenState>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {

        if(userService.emailExists(userDTO.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        if(userDTO.getPassword().length() < PASSWORD_MIN_LENGTH || userDTO.getPassword().length() > PASSWORD_MAX_LENGTH) {
            return new ResponseEntity<>("Password size is not good", HttpStatus.BAD_REQUEST);
        }

        User user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/role/{token}")
    public ResponseEntity<?> getRole(@PathVariable String token) {
        String role = tokenUtils.getRole(token);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/username/{token}")
    public ResponseEntity<?> getUsername(@PathVariable String token) {
        String username = tokenUtils.getUsernameFromToken(token);

        return new ResponseEntity<>(username, HttpStatus.OK);
    }
}
