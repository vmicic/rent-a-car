package com.rentacar.agentbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rentacar.agentbackend.domain.Company;
import com.rentacar.agentbackend.domain.dto.CompanyDTO;
import com.rentacar.agentbackend.repository.CompanyRepository;
import com.rentacar.agentbackend.service.CompanyService;
import com.rentacar.agentbackend.ws.client.RegisterCompanyClient;
import com.rentacar.agentbackend.ws.wsdl.RegisterCompanyResponse;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;
	private final RegisterCompanyClient registerCompanyClient;
	
	public CompanyServiceImpl(CompanyRepository companyRepository, RegisterCompanyClient registerCompanyClient) {
		this.companyRepository = companyRepository;
		this.registerCompanyClient = registerCompanyClient;
	}
	
	
	
	@Override
	public Company createCompany(CompanyDTO companyDTO) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Company findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Company updateCompany(Long id, CompanyDTO companyDTO) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void deleteCompany(Long id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String registerCompany() {
		Optional<Company> thisCompany = companyRepository.findById(new Long(1));
		RegisterCompanyResponse response = registerCompanyClient.registerCompany(thisCompany.get());
		return response.getResponse();
	}

}
