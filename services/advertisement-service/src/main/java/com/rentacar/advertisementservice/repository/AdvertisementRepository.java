package com.rentacar.advertisementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.Advertisement;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{

    List<Advertisement> findAllByUserId(Long id);


}
