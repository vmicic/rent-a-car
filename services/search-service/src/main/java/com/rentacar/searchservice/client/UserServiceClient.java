package com.rentacar.searchservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/auth/role/{token}")
    String getRole(@PathVariable String token);

    @GetMapping("/auth/username/{token}")
    String getUsername(@PathVariable String token);
}
