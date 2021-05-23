package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
