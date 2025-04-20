package com.advancedjava.spring_security_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.advancedjava.spring_security_demo.model.Users;
import com.advancedjava.spring_security_demo.repo.UsersRepo;

@Service
public class UserService {
	
	@Autowired
	private UsersRepo userRepo;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
	
	public Users register(Users user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	public String verify(Users user) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}
		return "Fail";
	}
	
}
