package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByNameEquals(String name);
}
