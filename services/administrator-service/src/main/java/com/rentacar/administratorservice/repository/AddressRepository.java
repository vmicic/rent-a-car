package com.rentacar.administratorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.administratorservice.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
