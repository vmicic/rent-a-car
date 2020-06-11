package com.rentacar.userservice.repository;

import com.rentacar.userservice.domain.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{

    List<Advertisement> findAllByUserId(Long id);


}
