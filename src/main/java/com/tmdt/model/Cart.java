package com.tmdt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.Data;

@Data
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "cart")
	private List<ItemInCart> itemInCarts;
	
}