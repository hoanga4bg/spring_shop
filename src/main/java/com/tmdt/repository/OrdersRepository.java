package com.tmdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.ItemInCart;
import com.tmdt.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{
	public Orders findOneById(Long id);

	public Orders findOneById(long id);

	public List<Orders> findByStatus(int status);

	public Orders findByItem(ItemInCart item);
	
	

}
