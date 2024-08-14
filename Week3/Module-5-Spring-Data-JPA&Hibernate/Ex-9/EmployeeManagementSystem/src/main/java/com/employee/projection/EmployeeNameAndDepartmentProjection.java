package com.employee.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeNameAndDepartmentProjection {

    @Value("#{target.name}")
    String getName();

    @Value("#{target.departmentName}")
    String getDepartmentName();
}
