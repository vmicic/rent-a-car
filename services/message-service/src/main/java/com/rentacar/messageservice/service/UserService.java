package com.rentacar.messageservice.service;

import com.rentacar.messageservice.domain.User;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);
}
