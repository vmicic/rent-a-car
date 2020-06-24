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

		com.rentacar.agentbackend.ws.wsdl.Address newAddress = new com.rentacar.agentbackend.ws.wsdl.Address();

		newAddress.setCountry(company.getAddress().getCountry());
		newAddress.setState(company.getAddress().getState());
		newAddress.setCity(company.getAddress().getCity());
		newAddress.setPostalCode(company.getAddress().getPostalCode());
		newAddress.setStreet(company.getAddress().getStreet());
		newAddress.setNumber(company.getAddress().getNumber());
		newAddress.setId(company.getAddress().getId().toString());

		com.rentacar.agentbackend.ws.wsdl.Company newCompany = new com.rentacar.agentbackend.ws.wsdl.Company();

		newCompany.setAddress(newAddress);
		newCompany.setFirstName(company.getFirstName());
		newCompany.setLastName(company.getLastName());
		newCompany.setName(company.getName());
		newCompany.setEmail(company.getEmail());
		newCompany.setPassword(company.getPassword());
		newCompany.setBusinessIdNumber(company.getBusinessIdNumber());
		newCompany.setId(company.getId().toString());

		RegisterCompanyRequest request = new RegisterCompanyRequest();
		request.setCompany(newCompany);

		RegisterCompanyResponse response = (RegisterCompanyResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://localhost:8080/administrator-service/ws", request,
				new SoapActionCallback("http://www.administratorservice.rentacar.com/ws/company/RegisterCountryRequest"));

		return response;
	}
}
