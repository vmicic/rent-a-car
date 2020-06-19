package com.rentacar.agentbackend.service;

import com.rentacar.agentbackend.domain.Authority;

public interface AuthorityService {

    Authority findByName(String name);
}
