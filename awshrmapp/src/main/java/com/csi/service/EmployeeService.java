package com.csi.service;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepoImpl;

    public Employee save(Employee employee){
        return employeeRepoImpl.save(employee);
    }

    public boolean signIn(String empEmailId,String empPassword){
        boolean flag=false;
        Employee employee=employeeRepoImpl.findByEmpEmailIdAndEmpPassword(empEmailId,empPassword);
        if(employee!=null && employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
            flag=true;
        }
        return flag;
    }

    public Optional<Employee> findById(int empId){
        return employeeRepoImpl.findById(empId);
    }

    public List<Employee> findAll(){
        return employeeRepoImpl.findAll();
    }

    public Employee update(Employee employee){
        return employeeRepoImpl.save(employee);
    }

    public void deleteById(int empId){
         employeeRepoImpl.deleteById(empId);
    }

    public void deleteAll(){
        employeeRepoImpl.deleteAll();
    }
}
