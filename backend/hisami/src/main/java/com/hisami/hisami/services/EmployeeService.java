package com.hisami.hisami.services;

import java.util.List;
import java.util.Optional;

import com.hisami.hisami.dto.EmployeeDTO;
import com.hisami.hisami.entities.Employee;

public interface EmployeeService {
    public List<Employee> getEmployees();

    public Optional<Employee> find(String cpf);

    public Employee create(EmployeeDTO dto);

    public boolean exists(String cpf);
}
