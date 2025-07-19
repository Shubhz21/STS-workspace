package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bike {
	
	@Id
	private int bid;
	
	
	public int getBid(int bid) {
		return bid;
	}
	
	public void setBid() {
		this.bid=bid;
	}
	private String company;
	
	public String getCompany(String company) {
		return company;
	}
	
	public void setCompany() {
		this.company=company;
	}
	
	
	private String name;
	
	public String getName(String name) {
		return name;
	}
	
	public void setName() {
		this.name=name;
	}
	
	
	private int price;
	
	public int getPrice(int price) {
		return price;
	}
	
	public void setPrice() {
		this.price=price;
	}
	

}
