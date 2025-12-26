package com.hisami.hisami.services;

import java.util.Optional;

import com.hisami.hisami.entities.EmployeeRole;

public interface EmployeeRoleService {
    public Optional<EmployeeRole> find(String name);
}
