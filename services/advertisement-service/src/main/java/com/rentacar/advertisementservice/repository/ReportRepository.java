package com.rentacar.advertisementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
