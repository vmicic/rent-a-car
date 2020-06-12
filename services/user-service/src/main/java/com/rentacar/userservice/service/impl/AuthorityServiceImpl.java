package com.rentacar.userservice.service.impl;

import com.rentacar.userservice.domain.Authority;
import com.rentacar.userservice.repository.AuthorityRepository;
import com.rentacar.userservice.service.AuthorityService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority findByName(String name) {
        return this.authorityRepository.findByNameEquals(name);
    }
}
