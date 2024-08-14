package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import com.employee.entity.primary.Employee;
import com.employee.projection.*;
import com.employee.repository.primary.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        return employee.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam String name) {
        List<Employee> employees = employeeRepository.findByName(name);

        if (employees.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        else
            return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/department")
    public ResponseEntity<List<Employee>> getEmployeeByDepartmentName(@RequestParam String departmentName) {
        List<Employee> employees = employeeRepository.findByDepartmentName(departmentName);

        if (employees.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        else
            return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);

        return employee.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Employee>> getEmployeesPaged(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Employee> employeesPage = employeeRepository.findAll(pageable);

        return new ResponseEntity<>(employeesPage, HttpStatus.OK);
    }

    @GetMapping("/paged-and-sorted")
    public ResponseEntity<Page<Employee>> getEmployeesPagedAndSorted(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ){
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<Employee> employeesPage = employeeRepository.findAll(pageable);

        return new ResponseEntity<>(employeesPage, HttpStatus.OK);
    }

    @GetMapping("/names-emails")
    public ResponseEntity<List<EmployeeNameAndEmailDTO>> getEmployeeNamesAndEmails() {
        List<EmployeeNameAndEmailDTO> employees = employeeRepository.findEmployeeNamesAndEmails();

        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
    }

    @GetMapping("/names-departments")
    public ResponseEntity<List<EmployeeNameAndDepartmentProjection>> getEmployeeNamesAndDepartments() {
        List<EmployeeNameAndDepartmentProjection> employees = employeeRepository.findEmployeeNamesAndDepartments();

        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow();

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
