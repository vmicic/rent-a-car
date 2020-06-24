package com.rentacar.agentbackend.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.rentacar.agentbackend.ws.client.RegisterCompanyClient;

@Configuration
public class CompanyConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.rentacar.agentbackend.ws.wsdl");
		return marshaller;
	}

	@Bean
	public RegisterCompanyClient countryClient(Jaxb2Marshaller marshaller) {
		RegisterCompanyClient client = new RegisterCompanyClient();
		client.setDefaultUri("http://localhost:8080/administrator-service/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
