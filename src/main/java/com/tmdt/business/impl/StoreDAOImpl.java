package com.tmdt.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tmdt.business.StoreDAO;
import com.tmdt.config.MyUserDetails;
import com.tmdt.model.Account;
import com.tmdt.repository.AccountRepository;

@Service
public class StoreDAOImpl implements StoreDAO {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account getAccount() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=((MyUserDetails) principal).getUsername() ;
		Account account=accountRepository.findOneByUsername(username);

		return account;
	}

}
