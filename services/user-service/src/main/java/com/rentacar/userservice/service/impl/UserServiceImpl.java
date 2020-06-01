package com.rentacar.userservice.service.impl;

import com.rentacar.userservice.domain.User;
import com.rentacar.userservice.repository.UserRepository;
import com.rentacar.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public void suspendUserFromRenting(Long id) {

    }

    @Override
    public void activateUser(Long id) {

    }

    @Override
    public void deactivateUser(Long id) {

    }
}
