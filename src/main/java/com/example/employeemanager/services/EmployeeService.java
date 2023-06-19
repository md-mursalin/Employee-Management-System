package com.example.employeemanager.services;

import com.example.employeemanager.exceptions.UserNotFoundException;
import com.example.employeemanager.models.Employee;
import com.example.employeemanager.repositories.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    final EmployeeRepo employeeRepo;

    public Employee addEmp(Employee employee){
       return employeeRepo.save(employee);
    }

    public List<Employee>findAllEmp(){
        return employeeRepo.findAll();
    }

    public Employee updateEmp(Employee employee){
        return employeeRepo.save(employee);
    }

    public Employee findEmp(long id){
        return (employeeRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by Id " + id + " was not found")));

    }

    public void deleteEmp(long id){
        employeeRepo.deleteById(id);
    }
}
