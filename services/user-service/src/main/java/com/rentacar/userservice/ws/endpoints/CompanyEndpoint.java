package com.rentacar.userservice.ws.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.rentacar.userservice.domain.dto.CompanyDTO;
import com.rentacar.userservice.service.UserService;
import com.rentacar.userservice.ws.company.Company;
import com.rentacar.userservice.ws.company.RegisterCompanyRequest;
import com.rentacar.userservice.ws.company.RegisterCompanyResponse;

@Endpoint
public class CompanyEndpoint {
	private static final String NAMESPACE_URI = "http://www.userservice.rentacar.com/ws/company";
	private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 80;

	private final UserService userService;
	
	@Autowired
	public CompanyEndpoint(UserService userService) {
		this.userService = userService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerCompanyRequest")
	@ResponsePayload
	public RegisterCompanyResponse registerCompany(@RequestPayload RegisterCompanyRequest request) {
		Company recievedCompany = request.getCompany();
		CompanyDTO companyDTO = new CompanyDTO();
		RegisterCompanyResponse response = new RegisterCompanyResponse();
		
		if(userService.emailExists(recievedCompany.getEmail())) {
			response.setResponse("Email already exists");
			return response;
        }
        if(userService.businessIdNumberExists(recievedCompany.getBusinessIdNumber())) {
        	response.setResponse("Business Id number already exists");
			return response;
        }
        if(recievedCompany.getPassword().length() < PASSWORD_MIN_LENGTH) {
        	response.setResponse("Password is too short");
			return response;
        }
        if(recievedCompany.getPassword().length() > PASSWORD_MAX_LENGTH) {
        	response.setResponse("Password is too long");
			return response;
        }

        companyDTO.setCompanyName(recievedCompany.getCompanyName());
        companyDTO.setFirstName(recievedCompany.getFirstName());
        companyDTO.setLastName(recievedCompany.getLastName());
        companyDTO.setEmail(recievedCompany.getEmail());
        companyDTO.setPassword(recievedCompany.getPassword());
        companyDTO.setAddress(recievedCompany.getAddress());
        companyDTO.setBusinessIdNumber(recievedCompany.getBusinessIdNumber());
        
        userService.createCompany(companyDTO, false);

		response.setResponse("Accepted. Approval pending.");
		return response;
	}
}
