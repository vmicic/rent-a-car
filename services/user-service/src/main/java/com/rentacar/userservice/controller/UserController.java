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

    @PutMapping("/activate/{id}")
    public void activateUser(@PathVariable Long id){

    }

    @PutMapping("/deactivate/{id}")
    public void deactivateUser(@PathVariable Long id) {

    }

    @PutMapping("/user/{id}/{permissionId}")
    public void addPermission(@PathVariable Long id, @PathVariable Long permissionId) {

    }

    @DeleteMapping("/user/{id}/{permissionId}")
    public void removePermission(@PathVariable Long id, @PathVariable Long permissionId) {

    }
}
