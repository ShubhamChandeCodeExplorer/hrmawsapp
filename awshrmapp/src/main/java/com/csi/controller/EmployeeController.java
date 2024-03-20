package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee){
        log.info("###### SAVE DATA FOR "+employee.getEmpName());
        return new ResponseEntity<>(employeeServiceImpl.save(employee), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId,String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId,empPassword));
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(int empId){
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @PutMapping("/updatebyid/{empId}")
    public ResponseEntity<Employee> update(@PathVariable int empId,@Valid @RequestBody Employee employee){
        Employee employee1=employeeServiceImpl.findById(empId).orElseThrow(()->new RecordNotFoundException("Employee id Dosent Exist"));
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpEmailId(employee.getEmpEmailId());

        return new ResponseEntity<>(employeeServiceImpl.update(employee1),HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> daletebyId(int empId){
        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("Data deleted Sucessfully");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        employeeServiceImpl.deleteAll();
        return ResponseEntity.ok("All Data Deleted Sucessfully  ");
    }

    @GetMapping("/sortbyempname")
    public ResponseEntity<List<Employee>> sortbyempName(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList());
    }
}
