package com.tmdt.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.business.ShipmentDAO;
import com.tmdt.model.Shipment;
import com.tmdt.repository.ShipmentRepository;
@Service
public class ShipmentDAOImpl implements ShipmentDAO{

	@Autowired
	private ShipmentRepository shipRepo;
	@Override
	public List<Shipment> findAll() {
		
		return shipRepo.findAll();
	}

	@Override
	public Shipment save(Shipment t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shipment findOneById(long id) {
		// TODO Auto-generated method stub
		return shipRepo.findOneById(id);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shipment update(long id, Shipment t) {
		// TODO Auto-generated method stub
		return null;
	}

}
