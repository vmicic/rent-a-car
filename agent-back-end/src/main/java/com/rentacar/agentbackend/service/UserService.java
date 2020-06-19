package com.rentacar.agentbackend.service;

import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findOneByEmail(String email);

    User createUser(UserDTO userDTO);

    boolean emailExists(String email);

    boolean idExists(Long id);

    List<User> getAll();

    User findById(Long id);

    void deleteUser(Long id);

    void suspendUserFromRenting(Long id);

    void activateUser(Long id);

    void deactivateUser(Long id);

    List<User> getAllUsersNoAdmin();

}
