package com.example.demo.entity;



import jakarta.persistence.*;

@Entity

@Table(name="employeelistData")
public class Employee {
	
    @Id
    @SequenceGenerator(name = "mySeq", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeq")
    
    private Long id;

    private String name;
    
    private String email;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name =name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email =email;
	}
    
    
}

