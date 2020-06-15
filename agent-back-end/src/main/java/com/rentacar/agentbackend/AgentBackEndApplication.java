package com.rentacar.agentbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AgentBackEndApplication {
	
	@RequestMapping("/health")
	public String healthTest() {
		return "Alive";
	}

	public static void main(String[] args) {
		SpringApplication.run(AgentBackEndApplication.class, args);
	}

}
