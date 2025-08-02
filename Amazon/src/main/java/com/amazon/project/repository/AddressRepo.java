package com.amazon.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.project.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{

}
