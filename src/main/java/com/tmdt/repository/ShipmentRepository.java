package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long>{

	public Shipment findOneById(long id);

}
