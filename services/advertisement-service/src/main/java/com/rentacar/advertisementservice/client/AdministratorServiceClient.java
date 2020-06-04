package com.rentacar.advertisementservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rentacar.advertisementservice.domain.CarBrand;
import com.rentacar.advertisementservice.domain.CarClass;
import com.rentacar.advertisementservice.domain.CarModel;
import com.rentacar.advertisementservice.domain.FuelType;
import com.rentacar.advertisementservice.domain.TransmissionType;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "administrator-service")
public interface AdministratorServiceClient {
	
	@GetMapping("/model/{id}")
	CarModel getModel(@PathVariable Long id);

	@GetMapping("/brand/{id}")
	CarBrand getBrand(@PathVariable Long id);
	
	@GetMapping("/fuel-type/{id}")
	FuelType getFuelType(@PathVariable Long id);
	
	@GetMapping("/transmission-type/{id}")
	TransmissionType getTransmissionType(@PathVariable Long id);
	
	@GetMapping("/class/{id}")
	CarClass getCarClass(@PathVariable Long id);
}
