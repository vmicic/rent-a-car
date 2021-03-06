package com.rentacar.advertisementservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rentacar.advertisementservice.domain.User;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/auth/role/{token}")
    String getRole(@PathVariable String token);

    @GetMapping("/auth/username/{token}")
    String getUsername(@PathVariable String token);
    
    @GetMapping("/users/loggedIn")
    User getLoggedInUser();
}
