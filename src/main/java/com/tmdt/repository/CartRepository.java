package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
