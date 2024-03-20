package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    @Size(min = 2,message = "Employee Name Must Be valid")
    private String empName;
    private double empSalary;
    private String empAddress;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;
    @Email(message = "Employee Email must be valid")
    private String empEmailId;
    @Size(min = 4,message = "Employee password should have 4 character")
    private String empPassword;

}
