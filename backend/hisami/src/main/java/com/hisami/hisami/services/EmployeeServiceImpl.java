package com.hisami.hisami.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hisami.hisami.dto.EmployeeDTO;
import com.hisami.hisami.entities.Employee;
import com.hisami.hisami.exception.EntityAlreadyExistsException;
import com.hisami.hisami.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> find(String cpf) {
        return employeeRepository.findByCpf(cpf);
    }

    @Override
    public Employee create(EmployeeDTO dto) {
        // Verify conflict
        if (this.exists(dto.getCpf())) {
            throw new EntityAlreadyExistsException("Esse empregado já está cadastrado.");
        }

        // Create employee
        Employee employee = new Employee(
                dto.getCpf(),
                dto.getName(),
                dto.getSalary());
        return employeeRepository.save(employee);
    }

    @Override
    public boolean exists(String cpf) {
        return this.employeeRepository.findByCpf(cpf).isPresent();
    }

}
