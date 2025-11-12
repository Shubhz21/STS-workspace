package com.flipkart.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flipkart.project.entity.Product;
import com.flipkart.project.repo.DataRepo;

@Service
public class FlipRepoService {
	
	@Autowired
	private DataRepo dRepo;
	
	public List<Product> getAll(){
		return dRepo.findAll();
	}
	
	public void dltProduct(int id) {
		dRepo.deleteById(id);
	}
	
	public Product addPr(Product prdct) {
		return dRepo.save(prdct);
	}

	
}
