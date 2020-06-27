package com.rentacar.agentbackend.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.rentacar.agentbackend.domain.Company;
import com.rentacar.agentbackend.ws.wsdl.RegisterCompanyRequest;
import com.rentacar.agentbackend.ws.wsdl.RegisterCompanyResponse;

public class RegisterCompanyClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(RegisterCompanyClient.class);

	public RegisterCompanyResponse registerCompany(Company company) {

		com.rentacar.agentbackend.ws.wsdl.Company newCompany = new com.rentacar.agentbackend.ws.wsdl.Company();

		newCompany.setCompanyName(company.getName());
		newCompany.setFirstName(company.getFirstName());
		newCompany.setLastName(company.getLastName());
		newCompany.setEmail(company.getEmail());
		newCompany.setPassword(company.getPassword());
		newCompany.setAddress(company.getAddress().getStreet() + " " + company.getAddress().getNumber() + ", " + 
				company.getAddress().getCity() + " " + company.getAddress().getPostalCode() + ", " + company.getAddress().getCountry());
		newCompany.setBusinessIdNumber(company.getBusinessIdNumber());
		

		RegisterCompanyRequest request = new RegisterCompanyRequest();
		request.setCompany(newCompany);

		RegisterCompanyResponse response = (RegisterCompanyResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://localhost:8080/user-service/ws", request,
				new SoapActionCallback("http://www.userservice.rentacar.com/ws/company/RegisterCountryRequest"));

		return response;
	}
}
