package com.hisami.hisami.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hisami.hisami.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
    public List<Employee> findAll();
}
