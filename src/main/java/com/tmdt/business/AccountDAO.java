package com.tmdt.business;

import com.tmdt.model.Account;

public interface AccountDAO extends GeneralDAO<Account>{
	
	public Account findByUsername(String username);
}
