package com.rentacar.advertisementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.PriceList;

public interface PriceListRepository extends JpaRepository<PriceList, Long>{

}
