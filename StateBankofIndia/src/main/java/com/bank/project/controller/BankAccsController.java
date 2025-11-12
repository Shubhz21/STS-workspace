package com.bank.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.project.entity.AccountHolder;
import com.bank.project.service.BankService;


@Controller
public class BankAccsController {
	
	@Autowired
	private BankService bankService;
	
	
	@GetMapping("/getAll")
	public String showAllCust(Model model) {
		
List<AccountHolder> acchldrs = bankService.getAllCust();
        
        model.addAttribute("customers", acchldrs);
		
		return "accholders";
	}
	
    @GetMapping("/balance/{id}")
    public String showBalance(@PathVariable("id") Integer accNo, Model model) {
        Integer balance = bankService.getBalanceByAccNo(accNo);
        model.addAttribute("accNo", accNo);
        model.addAttribute("balance", balance);
        return "balance"; // goes to balance.html
    }

}
