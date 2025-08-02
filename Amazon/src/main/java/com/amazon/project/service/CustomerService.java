package com.amazon.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.project.entity.Customer;
import com.amazon.project.repository.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepo custRepo;                  //-------- DAO injection
	
	public List<Customer> getAllCustomers(){
		return custRepo.findAll();
	}
	
	public Customer getByCustomerId(long customerId) {
		
		return custRepo.getById(customerId);
	}
	
	public void deleteByCustomerId(long customerId) {
		 custRepo.deleteById(customerId);
	}
	
	public Customer updateCustomer(long customerId, Customer newCustomer) {
		
		Customer oldCustomer = custRepo.getById(customerId);
		
		oldCustomer.setCustomerName(newCustomer.getCustomerName());
		oldCustomer.setEmail(newCustomer.getEmail());
		oldCustomer.setPhone(newCustomer.getPhone());
		
		
		return custRepo.save(oldCustomer);
	}
	
	public Customer addNewCustomer(Customer newCust) {
		return custRepo.save(newCust);
	}
	
	

}
