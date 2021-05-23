package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
