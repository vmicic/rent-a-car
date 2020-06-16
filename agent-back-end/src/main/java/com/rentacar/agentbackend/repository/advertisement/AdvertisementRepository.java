package com.rentacar.agentbackend.repository.advertisement;

import com.rentacar.agentbackend.domain.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{

    List<Advertisement> findAllByUserId(Long id);


}
