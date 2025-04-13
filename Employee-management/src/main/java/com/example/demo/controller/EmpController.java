package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;

import java.util.List;

@Controller

public class EmpController {
	
	

    @Autowired
    private EmpService employeeService;
    

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
    	
        List<Employee> employees = employeeService.getAllEmployees();
        
        model.addAttribute("empList", employees);
       
        
        //System.out.println("this is list of employees");
        
        return "employeeList";  
    }

    
    @GetMapping("/add")
    public String showForm(Model model) {
    	
        model.addAttribute("employee", new Employee());
        
        return "addEmployee";
        
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employee emp) {
    	
        employeeService.addEmployee(emp);
        
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
    	
        employeeService.deleteEmployee(id);
        
        return "redirect:/employees";
    }
    
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Employee emp = employeeService.getEmployeeById(id);  // you'll create this in the service layer
        model.addAttribute("employee", emp);
        return "editEmployee";  // a new Thymeleaf template for editing
    }
    
    
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee emp) {
        employeeService.updateEmployee(emp);
        return "redirect:/employees";
    }
    
    
    
}
