package com.example.spring.demo.service;

import java.util.List;

import com.example.spring.demo.model.Customer;

public interface CustomerService {

	Customer save(Customer customer);
	void delete(Long id);
	List<Customer> getAllCustomer();
	Customer getCustomer(long id);
	
}
