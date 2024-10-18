package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerEntity;
import com.respository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@PutMapping
	public CustomerEntity updateCustomerProfile(@RequestBody CustomerEntity customerEntity) {
		customerRepository.save(customerEntity);
		return customerEntity;
	}

	@GetMapping
	public List<CustomerEntity> getAllCustomers() {

		return customerRepository.findAll();

	}

}
