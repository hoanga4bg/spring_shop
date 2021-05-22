package com.tmdt.model;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Staff {
	private Long id;
	private String name;
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account;
}
