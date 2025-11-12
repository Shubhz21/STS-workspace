package com.bank.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.project.entity.AccountHolder;

@Repository
public interface BankRepo extends JpaRepository<AccountHolder, Integer> {

}
