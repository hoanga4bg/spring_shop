package com.tmdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Customer;
import com.tmdt.model.Loves;
import com.tmdt.model.Product;

public interface LoveRepository extends JpaRepository<Loves, Long>{

	public List<Loves> findByProduct(Product p);



	public Loves findOneByProductAndCustomer(Product p, Customer c);

}
