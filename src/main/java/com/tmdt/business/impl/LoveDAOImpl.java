package com.tmdt.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.business.LovesDAO;
import com.tmdt.model.Customer;
import com.tmdt.model.Loves;
import com.tmdt.model.Product;
import com.tmdt.repository.LoveRepository;
@Service
public class LoveDAOImpl implements LovesDAO{
	
	@Autowired
	private LoveRepository loveRepo;
	@Override
	public List<Loves> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loves save(Loves t) {
		
		return loveRepo.save(t);
	}

	@Override
	public Loves findOneById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		loveRepo.deleteById(id);
		
	}

	@Override
	public Loves update(long id, Loves t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loves> findAllByProduct(Product p) {
		List<Loves> list=loveRepo.findByProduct(p);
		return list;
	}

	@Override
	public Loves findByProductAndCustomer(Product p, Customer c) {
		
		return loveRepo.findOneByProductAndCustomer(p,c);
	}

}
