package com.employee.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.entity.primary.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d from Department d WHERE LOWER(d.name) = LOWER(?1)")
    Department findByName(String name);
}
