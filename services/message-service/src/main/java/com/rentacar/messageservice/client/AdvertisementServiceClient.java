package com.rentacar.messageservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "advertisement-service")
public interface AdvertisementServiceClient {

    @GetMapping("/reservation/messages/{email}")
    boolean canUsersExchangeMessages(@PathVariable String email);
}
