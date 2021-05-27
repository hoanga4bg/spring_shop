package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Cart;
import com.tmdt.model.Customer;

public interface CartRepository extends JpaRepository<Cart, Long>{

	public Cart findOneByCustomer(Customer cus);

}
