package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*" )   //CORS POLICY
@RestController
@RequestMapping(path= "/api/v2/department")
public class DepartmentController
{
    @Autowired
    private DepartmentRepository departmentRepository;
    @GetMapping
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    //create dept rest api

    @PostMapping
    public  Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);

    }
    //get department api
    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id){
        Department department = departmentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Department not exist with id:"+id));
        return ResponseEntity.ok(department);
    }

    //update Department api
    @PutMapping("{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable long id,@RequestBody Department department){
        Department updateDepartment = departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department not exist with id:"+id));
        updateDepartment.setDepartmentname(department.getDepartmentname());
        updateDepartment.setFloor(department.getFloor());
        updateDepartment.setArea(department.getArea());

        departmentRepository.save(updateDepartment);
        return ResponseEntity.ok(updateDepartment);
    }
    @DeleteMapping("{id}")
    //delete department
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable long id){
        Department department = departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department not exist with id:"+id));

        departmentRepository.delete(department);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
