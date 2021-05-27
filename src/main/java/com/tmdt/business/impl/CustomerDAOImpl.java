package com.tmdt.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.tmdt.business.CustomerDAO;
import com.tmdt.model.Account;
import com.tmdt.model.Cart;
import com.tmdt.model.Customer;
import com.tmdt.repository.AccountRepository;
import com.tmdt.repository.CartRepository;
import com.tmdt.repository.CustomerRepository;
import com.tmdt.config.*;
@Service
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private CartRepository cartRepo;
	@Override
	public Customer getCustomer() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=((MyUserDetails) principal).getUsername() ;
		Account account=accountRepo.findOneByUsername(username);
		System.out.print(account.getUsername());
		Customer customer=customerRepo.findByAccount(account);
		System.out.print(customer.getId());
		return customer;
	}


	
}
