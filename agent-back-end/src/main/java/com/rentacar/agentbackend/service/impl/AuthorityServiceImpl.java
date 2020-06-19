package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.Authority;
import com.rentacar.agentbackend.repository.AuthorityRepository;
import com.rentacar.agentbackend.service.AuthorityService;

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
