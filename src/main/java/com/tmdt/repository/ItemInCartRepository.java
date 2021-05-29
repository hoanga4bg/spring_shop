package com.tmdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;

public interface ItemInCartRepository extends JpaRepository<ItemInCart, Long>{

	public List<ItemInCart> findByCartAndStatus(Cart cart, boolean b);
	
	public ItemInCart findOneById(Long id);
	
	public List<ItemInCart> findByCart(Cart cart);

}
