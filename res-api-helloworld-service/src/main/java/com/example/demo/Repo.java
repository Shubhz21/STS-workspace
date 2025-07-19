package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface Repo extends JpaRepository<Asus, Long> {

	@Modifying
	@Transactional
	@Query(value = "insert into asus (id,name) values (?1,?2)",nativeQuery = true)
	void insertData(long id,String name);
}
