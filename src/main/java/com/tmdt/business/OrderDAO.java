package com.tmdt.business;

import java.util.List;

import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;
import com.tmdt.model.Orders;
import com.tmdt.model.Payment;
import com.tmdt.model.Shipment;

public interface OrderDAO extends GeneralDAO<Orders>{
	public void updateItemStatus(ItemInCart item);
	
	public List<Orders> findByCart(Cart cart);
}
