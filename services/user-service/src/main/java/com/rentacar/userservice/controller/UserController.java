package com.rentacar.userservice.controller;

import com.rentacar.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public void getAllUsers() {
        System.out.println("POGODIO");
    }

    @GetMapping("/{id}")
    public void getUser(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/suspend/{id}")
    public void suspendUserFromRenting(@PathVariable Long id) {

    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateUser(@PathVariable Long id){
        if(!userService.idExists(id)) {
            return new ResponseEntity<>("User with id does not exists", HttpStatus.BAD_REQUEST);
        }

        userService.activateUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        if(!userService.idExists(id)) {
            return new ResponseEntity<>("User with id does not exists", HttpStatus.BAD_REQUEST);
        }
        userService.deactivateUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/lock/{id}")
    public ResponseEntity<?> lockUser(@PathVariable Long id) {
        if(!userService.idExists(id)) {
            return new ResponseEntity<>("User with id does not exists", HttpStatus.BAD_REQUEST);
        }
        userService.lockUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/unlock/{id}")
    public ResponseEntity<?> unlockUser(@PathVariable Long id) {
        if(!userService.idExists(id)) {
            return new ResponseEntity<>("User with id does not exists", HttpStatus.BAD_REQUEST);
        }
        userService.unlockUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    
}
