package com.tmdt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private boolean status;
	@OneToMany(mappedBy = "orders")
	private List<ItemInCart> list;
	
	@ManyToOne
	@JoinColumn(name="payment_id")
	private Payment payment;
	@ManyToOne
	@JoinColumn(name="shipment_id")
	private Shipment shipment;
}
