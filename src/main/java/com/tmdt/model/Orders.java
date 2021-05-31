package com.tmdt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private boolean status;
	private double totalPrice;
	private String address;
	private String phone;
	private boolean statusStore;
	@OneToOne
	@JoinColumn(name = "item_id")
	private ItemInCart item;
	
	@ManyToOne
	@JoinColumn(name="payment_id")
	private Payment payment;
	@ManyToOne
	@JoinColumn(name="shipment_id")
	private Shipment shipment;
}
