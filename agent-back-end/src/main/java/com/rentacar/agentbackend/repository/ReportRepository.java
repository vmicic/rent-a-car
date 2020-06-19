package com.rentacar.agentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentacar.agentbackend.domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
