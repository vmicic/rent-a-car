package com.rentacar.userservice.service.impl;

import com.rentacar.userservice.domain.Authority;
import com.rentacar.userservice.domain.User;
import com.rentacar.userservice.domain.dto.CompanyDTO;
import com.rentacar.userservice.domain.dto.UserDTO;
import com.rentacar.userservice.repository.UserRepository;
import com.rentacar.userservice.security.auth.AuthoritiesConstants;
import com.rentacar.userservice.service.AuthorityService;
import com.rentacar.userservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthorityService authorityService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }


    @Override
    public User findOneByEmail(String email) {
        return this.userRepository.findOneByEmailIgnoreCase(email).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
    	return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void suspendUserFromRenting(Long id) {

    }


	@Override
    public User activateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user != null) {
            user.setEnabled(true);
            return this.userRepository.save(user);
        }else {
        	return null;
        }
    }

    @Override
    public User deactivateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user != null) {
            user.setEnabled(false);
            return this.userRepository.save(user);
        }else {
        	return null;
        }
    }



    @Override
    public boolean emailExists(String email) {
        return this.userRepository.findOneByEmailIgnoreCase(email).isPresent();
    }
    
    @Override
	public boolean businessIdNumberExists(String number) {
    	return this.userRepository.findOneByBusinessIdNumber(number).isPresent();
	}

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDateRegistered(LocalDateTime.now());
        user.setEnabled(false);

        return this.userRepository.save(user);
    }
    

    @Override
	public User createCompany(CompanyDTO companyDTO, boolean automaticallyEnabled) {
    	User user = new User();

        user.setFirstName(companyDTO.getFirstName());
        user.setLastName(companyDTO.getLastName());
        user.setEmail(companyDTO.getEmail());
        user.setCompanyName(companyDTO.getCompanyName());
        user.setPassword(passwordEncoder.encode(companyDTO.getPassword()));
        user.setBusinessIdNumber(companyDTO.getBusinessIdNumber());
        user.setAddress(companyDTO.getAddress());
        user.getAuthorities().add(authorityService.findByName("ROLE_COMPANY"));
        user.setDateRegistered(LocalDateTime.now());
        user.setEnabled(automaticallyEnabled);

        return this.userRepository.save(user);
	}


	@Override
    public boolean idExists(Long id) {
        return this.userRepository.findById(id).isPresent();
    }


	@Override
    public List<User> getAllUsersNoAdmin() {
        return this.userRepository.findAllByAuthoritiesContainsOrAuthoritiesContainsOrAuthoritiesContains(
                authorityService.findByName(AuthoritiesConstants.USER), authorityService.findByName(AuthoritiesConstants.AGENT), authorityService.findByName(AuthoritiesConstants.COMPANY));

    }
}
