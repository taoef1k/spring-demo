package com.example.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.demo.exception.ResourceNotFoundException;
import com.example.spring.demo.model.Customer;
import com.example.spring.demo.repository.CustomerRepository;
import com.example.spring.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		if(null != customer.getId() && customer.getId().longValue() > 0) {
			Customer temp = customerRepository.findById(customer.getId())
		            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customer.getId()));

			temp.setName(customer.getName());
			temp.setBirthDate(customer.getBirthDate());
			temp.setPhoto(customer.getPhoto());

		    return customerRepository.save(temp);
		}
		return customerRepository.save(customer);
	}

	@Override
	public void delete(Long id) {
		Customer customer = customerRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
		
		customerRepository.delete(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(long id) {
		Customer customer = customerRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
		
		return customer;
	}

}