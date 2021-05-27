package com.tmdt.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.business.AccountDAO;
import com.tmdt.model.Account;
import com.tmdt.model.Customer;
import com.tmdt.repository.AccountRepository;
import com.tmdt.repository.CustomerRepository;


@Service
public class AccountDAOImp implements AccountDAO{
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account save(Account t) {
		t.setRole("ROLE_USER");
		accountRepo.save(t);
		Account a=findByUsername(t.getUsername());
		Customer cus=new Customer();
		cus.setAccount(a);
		customerRepo.save(cus);
		return t;
	}

	@Override
	public Account findOneById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account update(long id, Account t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findByUsername(String username) {
		Account account=accountRepo.findOneByUsername(username);
		return account;
	}

}
