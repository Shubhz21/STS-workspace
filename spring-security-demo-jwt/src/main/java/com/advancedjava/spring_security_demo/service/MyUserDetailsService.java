package com.advancedjava.spring_security_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.advancedjava.spring_security_demo.model.UserPrincipal;
import com.advancedjava.spring_security_demo.model.Users;
import com.advancedjava.spring_security_demo.repo.UsersRepo;

@Service 
public class MyUserDetailsService implements UserDetailsService{

	@Autowired 
	UsersRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = userRepo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new UserPrincipal(user);
	}

}
