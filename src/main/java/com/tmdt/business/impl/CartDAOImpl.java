package com.tmdt.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.business.CartDAO;
import com.tmdt.business.CustomerDAO;
import com.tmdt.model.Cart;
import com.tmdt.model.Customer;
import com.tmdt.model.ItemInCart;
import com.tmdt.repository.CartRepository;
import com.tmdt.repository.ItemInCartRepository;

@Service
public class CartDAOImpl implements CartDAO{
	@Autowired
	private CustomerDAO cusDAO;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ItemInCartRepository itemRepo;
	@Override
	public Cart getCart() {
		Customer cus=cusDAO.getCustomer();
		Cart cart=cartRepo.findOneByCustomer(cus);
		return cart;
	}

	@Override
	public List<ItemInCart> getItemInCart(Cart cart) {
		List<ItemInCart> list=itemRepo.findByCartAndStatus(cart,true);
		return list;
	}

	@Override
	public List<Cart> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart save(Cart t) {
		
		return cartRepo.save(t);
	}

	@Override
	public Cart findOneById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cart update(long id, Cart t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToCart(ItemInCart iic) {
		itemRepo.save(iic);
		
	}

	@Override
	public ItemInCart getItemInCartById(Long id) {
		
		return itemRepo.findOneById(id);
	}

}
