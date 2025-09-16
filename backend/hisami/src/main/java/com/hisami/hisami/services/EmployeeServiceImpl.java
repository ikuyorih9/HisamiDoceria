package com.hisami.hisami.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hisami.hisami.entities.Employee;
import com.hisami.hisami.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

}
