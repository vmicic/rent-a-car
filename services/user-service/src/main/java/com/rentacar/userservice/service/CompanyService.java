package com.rentacar.userservice.service;

import com.rentacar.userservice.domain.Company;
import com.rentacar.userservice.domain.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    Company createCompany(CompanyDTO companyDTO);

    Company findById(Long id);

    List<Company> getAllCompanies();

    Company updateCompany(Long id, CompanyDTO companyDTO);

    void deleteCompany(Long id);
}
