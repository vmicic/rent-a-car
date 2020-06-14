package com.rentacar.messageservice.service.impl;

import com.rentacar.messageservice.domain.User;
import com.rentacar.messageservice.repository.UserRepository;
import com.rentacar.messageservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
