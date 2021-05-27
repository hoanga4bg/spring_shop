package com.tmdt.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.business.ProductDAO;
import com.tmdt.model.Product;
import com.tmdt.repository.ProductRepository;


@Service
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	private ProductRepository proRepo;
	@Override
	public List<Product> findAll() {
		
		return proRepo.findAll();
	}

	@Override
	public Product save(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findOneById(long id) {
		Product p=new Product();
		p=proRepo.findOneById(id);
		return p;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product update(long id, Product t) {
		// TODO Auto-generated method stub
		return null;
	}

}
