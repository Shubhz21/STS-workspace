package com.bank.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.project.entity.AccountHolder;
import com.bank.project.repository.BankRepo;

@Service
public class BankService {
	
	@Autowired
	private BankRepo repo;
	
	
	public List<AccountHolder> getAllCust(){
		return repo.findAll();
	}
	
    public Integer getBalanceByAccNo(int accNo) {
        AccountHolder cust = repo.findById(accNo)
                            .orElseThrow(() -> new RuntimeException("Account not found"));
        return cust.getAccBalance();
    }

}
