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
        this.userRepository.deleteById(id);
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public boolean idExists(Long id) {
        return this.userRepository.findById(id).isPresent();
    }


}
