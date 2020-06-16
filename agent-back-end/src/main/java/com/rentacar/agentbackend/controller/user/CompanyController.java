package com.rentacar.agentbackend.controller.user;

import com.rentacar.agentbackend.domain.dto.AgentDTO;
import com.rentacar.agentbackend.domain.dto.CompanyDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

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
}
