package com.tmdt.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.business.PaymentDAO;
import com.tmdt.model.Payment;
import com.tmdt.repository.PaymentRepository;
@Service
public class PaymentDAOImpl implements PaymentDAO{

	@Autowired
	private PaymentRepository paymentRepo;
	@Override
	public List<Payment> findAll() {
		
		return paymentRepo.findAll();
	}

	@Override
	public Payment save(Payment t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment findOneById(long id) {
		// TODO Auto-generated method stub
		return paymentRepo.findOneById(id);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Payment update(long id, Payment t) {
		// TODO Auto-generated method stub
		return null;
	}

}
