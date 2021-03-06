package com.rentacar.messageservice.repository;

import com.rentacar.messageservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailEqualsIgnoreCase(String email);
}
