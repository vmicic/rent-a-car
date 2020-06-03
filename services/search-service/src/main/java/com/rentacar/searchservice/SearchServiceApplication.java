package com.rentacar.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class SearchServiceApplication {


	@RequestMapping("/health")
	public String home() {
		return "Hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}

}
