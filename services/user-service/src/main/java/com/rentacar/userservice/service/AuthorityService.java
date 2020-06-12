package com.rentacar.userservice.service;

import com.rentacar.userservice.domain.Authority;

public interface AuthorityService {

    Authority findByName(String name);
}
