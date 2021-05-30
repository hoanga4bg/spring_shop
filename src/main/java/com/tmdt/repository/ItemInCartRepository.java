package com.tmdt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;
import com.tmdt.model.Orders;

public interface ItemInCartRepository extends JpaRepository<ItemInCart, Long>{

	public List<ItemInCart> findByCartAndStatus(Cart cart, boolean b);
	
	public ItemInCart findOneById(Long id);
	
	public List<ItemInCart> findByCart(Cart cart);
	
	public List<ItemInCart> findByOrders(Orders orders);
	
	List<ItemInCart> findByCreateDateBetween(Date date1,Date date2);

}
