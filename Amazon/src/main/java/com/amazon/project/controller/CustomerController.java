package com.amazon.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.project.entity.Address;
import com.amazon.project.entity.Customer;
import com.amazon.project.service.AddressService;
import com.amazon.project.service.CustomerService;

// @CrossOrigin()

@RestController
@RequestMapping(path = "/amazonApi")
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private AddressService addService;
	
	@GetMapping("/getAllCust")
	public List<Customer> getAllCust() {
		return custService.getAllCustomers();
	}
	
	@RequestMapping(path = "/getCustById/{customerId}",method = RequestMethod.GET)
	public Customer getCustById(long customerId) {
		
		return custService.getByCustomerId(customerId);
	}
	
	@RequestMapping(path = "/getAddsOfCust/{customerId}",method = RequestMethod.GET)
	public List<Address> getAllAddrByCustId(@PathVariable long customerId){
		
		return addService.getAddofCust(customerId);
	}
	
	@RequestMapping(path = "/newAdd/{customerId}",method = RequestMethod.POST)
	public ResponseEntity<Address> addNewAddtoCust(@PathVariable long customerId, @RequestBody Address newAddress){
		
		Address saveAdd = addService.addNewAddtoExistCust(customerId, newAddress);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveAdd);
	}
	
	@RequestMapping(path = "/editAdd/{customerId}/adds/{addressId}", method = RequestMethod.PUT)
	public ResponseEntity<Address> updateAdds(@PathVariable long customerId,@RequestBody Address newAdd, @PathVariable long addressId){
		
		Address saveAdd = addService.updateAddofExistCust(customerId,newAdd,addressId);
		
		return ResponseEntity.status(HttpStatus.OK).body(saveAdd);
	}
	
	@RequestMapping(path = "/dltAdd/{customerId}/adds/{addressId}",method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePerticularAdd(@PathVariable long customerId, @PathVariable long addressId){
		
		 addService.deleteAddofCust(customerId, addressId);
		 return ResponseEntity.status(HttpStatus.OK).body("Address deleted successfully addressId:"+addressId);
	}
	
	
	@PutMapping("/editCust/{customerId}")
	public Customer updateCust(@PathVariable long customerId,@RequestBody Customer cust) {
		return custService.updateCustomer(customerId, cust);
	}
	
	@PostMapping("/addCust")
	public Customer addNewCust(@RequestBody Customer cust) {
		return custService.addNewCustomer(cust);
	}
	
	@DeleteMapping("/delCust/{customerId}")
	public void deleteCust(@PathVariable long customerId) {
		custService.deleteByCustomerId(customerId);
	}
	
	
	
	
	

}
