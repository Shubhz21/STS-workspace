package com.flipkart.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipkart.project.entity.Product;



@Repository
public interface DataRepo extends JpaRepository<Product, Integer> {

}
