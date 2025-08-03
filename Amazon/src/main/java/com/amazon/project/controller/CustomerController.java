package com.amazon.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.amazon.project.entity.Customer;
import com.amazon.project.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	@GetMapping("/get")
	public List<Customer> getAllCust() {
		return custService.getAllCustomers();
	}
	
	@PutMapping("/edit/{customerId}")
	public Customer updateCust(@PathVariable long customerId,@RequestBody Customer cust) {
		return custService.updateCustomer(customerId, cust);
	}
	
	@PostMapping("/add")
	public Customer addNewCust(@RequestBody Customer cust) {
		return custService.addNewCustomer(cust);
	}
	
	@DeleteMapping("/del/{customerId}")
	public void deleteCust(@PathVariable long customerId) {
		custService.deleteByCustomerId(customerId);
	}
	

}
