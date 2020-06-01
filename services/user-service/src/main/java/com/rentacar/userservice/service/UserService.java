package com.rentacar.userservice.service;

import com.rentacar.userservice.domain.User;

import java.util.List;

public interface UserService {

    User findOneByEmail(String email);

    List<User> getAll();

    User findById(Long id);

    void deleteUser(Long id);

    void suspendUserFromRenting(Long id);

    void activateUser(Long id);

    void deactivateUser(Long id);

}
