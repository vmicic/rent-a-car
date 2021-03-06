package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.UserDTO;
import com.rentacar.agentbackend.repository.UserRepository;
import com.rentacar.agentbackend.security.auth.AuthoritiesConstants;
import com.rentacar.agentbackend.service.AuthorityService;
import com.rentacar.agentbackend.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    	return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void suspendUserFromRenting(Long id) {

    }

    @Override
    public void activateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user != null) {
            user.setEnabled(true);
            this.userRepository.save(user);
        }
    }

    @Override
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user != null) {
            user.setEnabled(false);
            this.userRepository.save(user);
        }
    }



    @Override
    public boolean emailExists(String email) {
        return this.userRepository.findOneByEmailIgnoreCase(email).isPresent();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(false);

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
