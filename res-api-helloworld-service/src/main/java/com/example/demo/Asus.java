package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Asus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	private Long id;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name=name;
	}
	
	

}
