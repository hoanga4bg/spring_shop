package com.tmdt.business;

import java.util.List;

import com.tmdt.model.Customer;
import com.tmdt.model.Loves;
import com.tmdt.model.Product;

public interface LovesDAO extends GeneralDAO<Loves>{
	public List<Loves> findAllByProduct(Product p);
	
	public Loves findByProductAndCustomer(Product p, Customer c);
}
