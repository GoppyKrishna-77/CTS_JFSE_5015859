package com.employee.repository.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.entity.primary.Employee;
import com.employee.projection.EmployeeNameAndDepartmentProjection;
import com.employee.projection.EmployeeNameAndEmailDTO;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByDepartmentName(String departmentName);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.email) = LOWER(:email)")
    Optional<Employee> findByEmail(@Param("email") String email);

    // Interface Projection
    @Query("SELECT e.name AS name, d.name AS departmentName FROM Employee e JOIN e.department d")
    List<EmployeeNameAndDepartmentProjection> findEmployeeNamesAndDepartments();

    // Constructor Projection
    @Query("SELECT new com.employee.projection.EmployeeNameAndEmailDTO(e.name, e.email) FROM Employee e")
    List<EmployeeNameAndEmailDTO> findEmployeeNamesAndEmails();
    
}
