package com.advancedjava.spring_security_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advancedjava.spring_security_demo.model.Users;

public interface UsersRepo extends JpaRepository<Users, Integer>{
	Users findByUsername(String username);
}
