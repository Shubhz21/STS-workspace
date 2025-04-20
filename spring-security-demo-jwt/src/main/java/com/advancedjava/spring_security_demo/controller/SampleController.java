package com.advancedjava.spring_security_demo.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SampleController {

	@GetMapping("/test")
	public String test() {
		return "hello";
	}
	
	@GetMapping("/print")
	public String test(HttpServletRequest req) {
		return "hello" + req.getSession().getId();
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest req) {
		return  (CsrfToken) req.getAttribute("_csrf");
	}
	
	@GetMapping("/csrf-demo")
	public String csrfDemo() {
		return "csrf" ;
	}
	
	@PostMapping("/csrf-demo")
	public String csrfDemoPost() {
		return "csrf" ;
	}
	
}
