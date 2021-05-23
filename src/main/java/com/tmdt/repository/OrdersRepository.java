package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
