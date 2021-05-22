package com.tmdt.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import lombok.Data;

@Data
@Entity
public class ItemInCart {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer amount;
	
	private Date createDate;
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name="orders_id")
	private Orders orders;
	
	
	
}
