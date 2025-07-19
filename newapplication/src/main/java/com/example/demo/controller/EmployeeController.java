package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {
	
	@RequestMapping(path = "/add",method = RequestMethod.GET)
	public String reQuest() {
		
		
		return "addEmployee";
	}
	
	@RequestMapping(path="/save",method = RequestMethod.POST)
	public String reSponse() {
		
		return "list";
	}
	
	

}
