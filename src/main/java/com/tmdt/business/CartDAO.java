package com.tmdt.business;

import java.util.List;

import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;

public interface CartDAO{
	public Cart getCart();

	List<ItemInCart> getItemInCart(Cart cart);

}
