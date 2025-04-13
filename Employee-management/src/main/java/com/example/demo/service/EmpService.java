package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

import com.example.demo.repository.EmpRepository;

import java.util.List;

@Service
public class EmpService {

    @Autowired
    private EmpRepository employeeDAO;

    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    public void deleteEmployee(int id) {
        employeeDAO.deleteById(id);
    }
    
    
    public void updateEmployee(Employee emp) {
        employeeDAO.save(emp); 
    }
    
    public Employee getEmployeeById(int id) {
        return employeeDAO.findById(id).orElse(null);
    }
    
    
}
