package com.rentacar.agentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentacar.agentbackend.domain.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long>{

}
