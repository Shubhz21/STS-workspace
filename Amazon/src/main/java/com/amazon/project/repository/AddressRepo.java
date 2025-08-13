package com.amazon.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amazon.project.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{
	
	
	@Query(value= "select * from address  where customer_id = :custId",nativeQuery = true)
	List<Address> findByCustId(@Param("custId") long customerId);
	
	
		
	

}
