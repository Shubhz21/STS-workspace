package com.example.demo.service;

import com.example.demo.entity.Employee;

import com.example.demo.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository userRepository;

    public List<Employee> getAllUsers() {
        return userRepository.findAll();
    }

    public Employee getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Employee addUser(Employee user) {
        return userRepository.save(user);
    }

    public Employee updateUser(Long id, Employee updatedUser) {
    	Employee existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());

        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
