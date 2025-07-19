package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity

public class Employee {
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "empseq24")
	
	@SequenceGenerator(name="empseq24",sequenceName = "empseq24",allocationSize = 1)
	
	private int id;
	
	private String name;
	
	private String location;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
