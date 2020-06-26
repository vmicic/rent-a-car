package com.rentacar.userservice.service;

import com.rentacar.userservice.domain.User;
import com.rentacar.userservice.domain.dto.CompanyDTO;
import com.rentacar.userservice.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findOneByEmail(String email);

    User createUser(UserDTO userDTO);
    
    User createCompany(CompanyDTO companyDTO);

    boolean emailExists(String email);

    boolean idExists(Long id);
    
    boolean businessIdNumberExists(String number);

    List<User> getAll();

    User findById(Long id);

    void deleteUser(Long id);

    void suspendUserFromRenting(Long id);

    void activateUser(Long id);

    void deactivateUser(Long id);

    List<User> getAllUsersNoAdmin();

}
