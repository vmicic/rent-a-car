package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.domain.User;
import com.rentacar.advertisementservice.repository.UserRepository;
import com.rentacar.advertisementservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
