package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Employee {

	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@SequenceGenerator(name = "em_seq", sequenceName = "esequence", allocationSize = 1)
	
	private long id;
	

	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	private int empid;
	
	public int getEmpid() {
		return empid;
	}
	
	public void setEmpid(int empid) {
		this.empid=empid;
	}
	
	private String gender;
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender=gender;
	}
	
	private String department;
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department=department;
	}
}
