package com.rentacar.administratorservice.ws.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.rentacar.administratorservice.domain.Address;
import com.rentacar.administratorservice.domain.Company;
import com.rentacar.administratorservice.domain.CompanyRegistrationState;
import com.rentacar.administratorservice.repository.AddressRepository;
import com.rentacar.administratorservice.repository.CompanyRepository;
import com.rentacar.administratorservice.ws.company.RegisterCompanyRequest;
import com.rentacar.administratorservice.ws.company.RegisterCompanyResponse;

@Endpoint
public class CompanyEndpoint {
	private static final String NAMESPACE_URI = "http://www.administratorservice.rentacar.com/ws/company";

	private CompanyRepository companyRepository;
	private AddressRepository addressRepository;

	@Autowired
	public CompanyEndpoint(CompanyRepository companyRepository, AddressRepository addressRepository) {
		this.companyRepository = companyRepository;
		this.addressRepository = addressRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerCompanyRequest")
	@ResponsePayload
	public RegisterCompanyResponse registerCompany(@RequestPayload RegisterCompanyRequest request) {
		
		Address newAddress = new Address();
		com.rentacar.administratorservice.ws.company.Address recievedAddress = request.getCompany().getAddress();

		newAddress.setCountry(recievedAddress.getCountry());
		newAddress.setState(recievedAddress.getState());
		newAddress.setCity(recievedAddress.getCity());
		newAddress.setPostalCode(recievedAddress.getPostalCode());
		newAddress.setStreet(recievedAddress.getStreet());
		newAddress.setNumber(recievedAddress.getNumber());

		Address savedAddress = addressRepository.save(newAddress);

		Company newCompany = new Company();
		com.rentacar.administratorservice.ws.company.Company recievedCompany = request.getCompany();

		newCompany.setFirstName(recievedCompany.getFirstName());
		newCompany.setLastName(recievedCompany.getLastName());
		newCompany.setName(recievedCompany.getName());
		newCompany.setEmail(recievedCompany.getEmail());
		newCompany.setPassword(recievedCompany.getPassword());
		newCompany.setBusinessIdNumber(recievedCompany.getBusinessIdNumber());
		newCompany.setAddress(savedAddress);
		newCompany.setRegistrationState(CompanyRegistrationState.PENDING);

		companyRepository.save(newCompany);

		RegisterCompanyResponse response = new RegisterCompanyResponse();
		response.setResponse("Accepted. Approval pending.");
		return response;
	}
}
