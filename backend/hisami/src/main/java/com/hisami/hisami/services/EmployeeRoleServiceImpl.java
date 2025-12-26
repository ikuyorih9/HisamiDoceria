package com.hisami.hisami.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hisami.hisami.entities.EmployeeRole;
import com.hisami.hisami.repositories.EmployeeRoleRepository;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {
    private final EmployeeRoleRepository employeeRoleRepository;

    public EmployeeRoleServiceImpl(EmployeeRoleRepository employeeRoleRepository) {
        this.employeeRoleRepository = employeeRoleRepository;
    }

    @Override
    public Optional<EmployeeRole> find(String name) {
        return this.employeeRoleRepository.findByName(name);
    }

}
