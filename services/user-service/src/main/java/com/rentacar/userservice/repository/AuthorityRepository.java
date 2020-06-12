package com.rentacar.userservice.repository;

import com.rentacar.userservice.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByNameEquals(String name);
}
