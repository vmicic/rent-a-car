package com.rentacar.administratorservice.repository;

import com.rentacar.administratorservice.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
