package com.tmdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Account;



public interface AccountRepository extends JpaRepository<Account, Long>{

	public Account findOneByUsername(String username);
	public List<Account> findByRole(String role);
	public Account findOneById(Long id);

}
