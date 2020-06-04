package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    boolean idExists(Long id);

    List<User> getAll();

    User findById(Long id);

    void deleteUser(Long id);

}
