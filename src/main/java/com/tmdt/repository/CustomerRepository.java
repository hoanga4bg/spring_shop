package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Account;
import com.tmdt.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Customer findOneByAccount(Account account);

}
