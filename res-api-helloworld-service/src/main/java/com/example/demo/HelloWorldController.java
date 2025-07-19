package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	//GET
	//URI- /hello-world
	//method = "Hello World"
	@Autowired
	private Repo repo;

	@RequestMapping(method=RequestMethod.GET,path="/hello")
	public String helloWorld() {
		return "hii world";
	}
	
	
	
	@GetMapping("/subget")
	public String getM(long id, String name) {
		repo.insertData(22, "shubham");
		return "data submitted"+" "+id+" "+name;
	}
}
