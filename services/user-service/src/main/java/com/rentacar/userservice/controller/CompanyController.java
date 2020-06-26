package com.rentacar.userservice.controller;

import com.rentacar.userservice.domain.User;
import com.rentacar.userservice.domain.dto.CompanyDTO;
import com.rentacar.userservice.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 80;
    
    private final UserService userService;
    
    public CompanyController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> registerCompany(@RequestBody CompanyDTO companyDTO) {

        if(userService.emailExists(companyDTO.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        if(userService.businessIdNumberExists(companyDTO.getBusinessIdNumber())) {
            return new ResponseEntity<>("Business Id number already exists", HttpStatus.BAD_REQUEST);
        }
        if(companyDTO.getPassword().length() < PASSWORD_MIN_LENGTH || companyDTO.getPassword().length() > PASSWORD_MAX_LENGTH) {
            return new ResponseEntity<>("Password size is not good", HttpStatus.BAD_REQUEST);
        }

        User user = userService.createCompany(companyDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
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
}
