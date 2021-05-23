package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Account;



public interface AccountRepository extends JpaRepository<Account, Long>{

	public Account findOneByUsername(String username);

}
