package rs.ac.uns.ftn.gateway.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/auth/role/{token}")
    String getRole(@PathVariable String token);
}
