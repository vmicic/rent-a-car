package com.rentacar.advertisementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{

}
