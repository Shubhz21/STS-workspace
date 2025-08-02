package com.amazon.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.project.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
