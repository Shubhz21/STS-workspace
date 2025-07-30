package com.project.cust.entities;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	
	private String customerName;
	
	private String eMail;
	
	private String phone;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
//	public List<Address> getAddress(){
//		return address;
//	}
//	
//	public void setAddress(List<Address> address){
//		this.address=address;
//	}
	
	
//	@OneToMany(mappedBy = "customer",fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
//	private List<Address> address = new ArrayList<>();
	
	
	

}
