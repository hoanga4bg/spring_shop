package com.tmdt.business;

import java.util.List;

import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;

public interface CartDAO extends GeneralDAO<Cart>{
	public Cart getCart();

	List<ItemInCart> getItemInCart(Cart cart);
	public void addToCart(ItemInCart iic);
	
	public ItemInCart getItemInCartById(Long id);
}
