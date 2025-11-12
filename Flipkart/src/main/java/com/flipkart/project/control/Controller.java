package com.flipkart.project.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.project.entity.Product;
import com.flipkart.project.service.FlipRepoService;

@RestController
public class Controller {
	
	@Autowired
	private FlipRepoService flipServ;
	
	@RequestMapping(method =RequestMethod.GET,path = "/getAll")
	public List<Product> showAll(){
		return flipServ.getAll();
	}
	
	public void dlt(int id) {
		flipServ.dltProduct(id);
	}

	@PostMapping("/addProduct")
	public Product addPr(@RequestBody Product pr) {
		return flipServ.addPr(pr);
	}
	
}
