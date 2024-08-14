package com.employee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE LOWER(e.name) = LOWER(:name)"),
        @NamedQuery(name = "Employee.findByDepartmentName", query = "SELECT e FROM Employee e JOIN e.department d WHERE LOWER(d.name) = LOWER(:departmentName)")
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    Department department;

}
