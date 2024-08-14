package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.employee.repository.DepartmentRepository;
import com.employee.entity.Department;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentRepository.save(department);

        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id){
        Optional<Department> department = departmentRepository.findById(id);

        return department.map(d -> new ResponseEntity<>(d, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartmentById(@PathVariable Long id, @RequestBody Department departmentDetails){
        Department department = departmentRepository.findById(id).orElseThrow();

        department.setName(departmentDetails.getName());

        Department updatedDepartment = departmentRepository.save(department);

        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable Long id) {
        departmentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
