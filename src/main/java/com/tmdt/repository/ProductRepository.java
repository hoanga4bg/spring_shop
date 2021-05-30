package com.tmdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	public Product findOneById(long id);

	public List<Product> findByName(String name);

}
