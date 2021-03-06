package com.rentacar.agentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentacar.agentbackend.domain.PriceList;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long>{

}
