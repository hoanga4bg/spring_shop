package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.ItemInCart;

public interface ItemInCartRepository extends JpaRepository<ItemInCart, Long>{

}
