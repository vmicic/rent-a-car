package com.rentacar.agentbackend.service.user;

import com.rentacar.agentbackend.domain.Authority;

public interface AuthorityService {

    Authority findByName(String name);
}
