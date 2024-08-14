package com.employee.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeNameAndEmailDTO {
    private String name;
    private String emailId;
}
