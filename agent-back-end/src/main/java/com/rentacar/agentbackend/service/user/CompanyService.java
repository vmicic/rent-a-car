package com.rentacar.agentbackend.service.user;

import com.rentacar.agentbackend.domain.Company;
import com.rentacar.agentbackend.domain.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    Company createCompany(CompanyDTO companyDTO);

    Company findById(Long id);

    List<Company> getAllCompanies();

    Company updateCompany(Long id, CompanyDTO companyDTO);

    void deleteCompany(Long id);
}
