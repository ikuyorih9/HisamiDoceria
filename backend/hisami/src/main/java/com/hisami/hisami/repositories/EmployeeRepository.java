package com.hisami.hisami.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hisami.hisami.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
    public List<Employee> findAll();
    public Optional<Employee> findByCpf(String cpf);
}
