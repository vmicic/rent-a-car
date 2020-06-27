package com.rentacar.userservice.controller;

import com.rentacar.userservice.domain.Authority;
import com.rentacar.userservice.domain.User;
import com.rentacar.userservice.domain.dto.CompanyDTO;
import com.rentacar.userservice.service.AuthorityService;
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
    private final AuthorityService authorityService;
    
    public CompanyController(UserService userService, AuthorityService authorityService) {
		super();
		this.userService = userService;
		this.authorityService = authorityService;
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

        User user = userService.createCompany(companyDTO, true);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
	
	@PutMapping("/approve/{id}")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> approveCompany(@PathVariable Long id) {
		User company = userService.findById(id);
    	Authority companyAuthority = authorityService.findByName("ROLE_COMPANY");
    	
    	if(company == null) {
            return new ResponseEntity<>("Company doesn't exist", HttpStatus.NOT_FOUND);
        }
    	if(!company.getAuthorities().contains(companyAuthority)) {
            return new ResponseEntity<>("User is not a company", HttpStatus.BAD_REQUEST);
        }
    	
		User retVal = userService.activateUser(company.getId());
		
		if(retVal != null) {
			return new ResponseEntity<>(retVal, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Unknown error during update", HttpStatus.BAD_REQUEST);
		}
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
