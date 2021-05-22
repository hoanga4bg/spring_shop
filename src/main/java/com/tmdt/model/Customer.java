package com.tmdt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "fullname_id")
	private FullName fullname;
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account;
}
