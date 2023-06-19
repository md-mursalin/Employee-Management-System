package com.example.employeemanager.controllers;

import com.example.employeemanager.models.Employee;
import com.example.employeemanager.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
     final EmployeeService employeeService;

     @GetMapping("/all")
     public ResponseEntity<List<Employee>>findAll(){               //this will reach by "/employee/all"
         List<Employee>employees =employeeService.findAllEmp();
         return new ResponseEntity<>(employees,HttpStatus.OK);
     }

     @GetMapping("/find/{id}")
     public ResponseEntity<Employee>findEmp(@PathVariable("id") long id){
         Employee employee =employeeService.findEmp(id);
         return new ResponseEntity<>(employee,HttpStatus.OK);
     }

     @PostMapping("/add")
     public ResponseEntity<Employee>addEmp(@RequestBody Employee employee){    //converting employee into json
         Employee newEmployee= employeeService.addEmp(employee);
         return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);

     }

     @PutMapping("/update")
     public ResponseEntity<Employee>updateEmp(@RequestBody Employee employee){
         Employee updateEmp= employeeService.updateEmp(employee);
         return new ResponseEntity<>(updateEmp,HttpStatus.OK);

     }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteEmp(@PathVariable("id") long id){
        employeeService.deleteEmp(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }





}
