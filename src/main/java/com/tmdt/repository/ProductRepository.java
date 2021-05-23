package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
