package com.rentacar.agentbackend.controller;

import com.rentacar.agentbackend.domain.dto.CompanyDTO;
import com.rentacar.agentbackend.service.CompanyService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	private final CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

    @PostMapping
    public void createCompany(@RequestBody CompanyDTO companyDTO) {

    }

    @GetMapping("/{id}")
    public void getSingleCompany(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllCompanies() {

    }

    @PutMapping("/{id}")
    public void updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {

    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {

    }
    
    @PostMapping("/register")
    public String registerCompany() {
    	return companyService.registerCompany();
    }
}
