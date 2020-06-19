package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.Authority;
import com.rentacar.agentbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmailIgnoreCase(String email);

    List<User> findAllByAuthoritiesContainsOrAuthoritiesContainsOrAuthoritiesContains(Authority authority, Authority authority2, Authority authority3);
}
