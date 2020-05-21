package com.rentacar.advertisementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long>{

}
