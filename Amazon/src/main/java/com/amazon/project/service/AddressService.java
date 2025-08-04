package com.amazon.project.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.project.entity.Address;
import com.amazon.project.entity.Customer;
import com.amazon.project.repository.AddressRepo;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepo addRepo;
	
	
	public List<Address> getAllAddr(){
		return addRepo.findAll();
	}
	
	public List<Address> getAddofCust(long customerId){
		return addRepo.findByCustId(customerId);
	}
	
	public Address addNewAddtoExistCust(long customerId, Address newAdd) {
		
		newAdd.setCustomerId(customerId);
		
		return addRepo.save(newAdd);
	}
	
	public Address updateAddofExistCust(long customerId, Address newAdd, long addressId) {
		
		Address oldAdd = addRepo.getById(addressId);
		
		if(!(oldAdd.getCustomerId()==customerId)) {
			
			
			throw new RuntimeException("This address does not belongs to customer id: "+customerId);
		}
		
		oldAdd.setAddr(newAdd.getAddr());
		oldAdd.setPincode(newAdd.getPincode());
		
		return addRepo.save(oldAdd);
		
	}
	
	public void deleteAddofCust(long customerId,long addressId) {
		
		Address address = addRepo.getById(addressId);
		
		if(address.getCustomerId() != customerId) {
			
			throw new RuntimeException("This address does not belongs to customer: "+customerId);
		}
		
		addRepo.deleteById(addressId);
	}
	
	
	
	
	
	
	
      

}
