package com.rentacar.agentbackend.repository.user;

import com.rentacar.agentbackend.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByNameEquals(String name);
}
