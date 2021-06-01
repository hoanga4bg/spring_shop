package com.tmdt.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.business.OrderDAO;
import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;
import com.tmdt.model.Orders;
import com.tmdt.repository.ItemInCartRepository;
import com.tmdt.repository.OrdersRepository;

@Service
public class OrderDAOImpl implements OrderDAO{
	
	@Autowired
	private OrdersRepository orderRepo;
	@Autowired
	private ItemInCartRepository itemRepo;
	@Override
	public List<Orders> findAll() {
		
		return orderRepo.findByStatus(0);
	}

	@Override
	public Orders save(Orders t) {
		
		return orderRepo.save(t);
	}

	@Override
	public Orders findOneById(long id) {
		
		return orderRepo.findOneById(id);
	}

	@Override
	public void delete(long id) {
		orderRepo.deleteById(id);
		
	}

	@Override
	public Orders update(long id, Orders t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateItemStatus(ItemInCart item) {
		itemRepo.save(item);
		
	}

	@Override
	public List<Orders> findByCart(Cart cart) {
		List<ItemInCart> list=itemRepo.findByCartAndStatus(cart, false);
		List<Orders> orders=new ArrayList<Orders>();
		for(ItemInCart item:list) {
			Orders o=orderRepo.findByItem(item);
			orders.add(o);
			
		}
		
		return orders;
		
	}
}
