package com.hisami.hisami.services;

import org.springframework.stereotype.Service;

import com.hisami.hisami.dto.EmployeeDTO;
import com.hisami.hisami.entities.Employee;
import com.hisami.hisami.entities.EmployeeAccount;
import com.hisami.hisami.entities.HasAccount;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
    private final EmployeeService employeeService;
    private final EmployeeAccountService employeeAccountService;
    private final HasAccountService hasAccountService;

    public AuthService(EmployeeService employeeService, EmployeeAccountService employeeAccountService,
            HasAccountService hasAccountService) {
        this.employeeService = employeeService;
        this.employeeAccountService = employeeAccountService;
        this.hasAccountService = hasAccountService;
    }

    @Transactional
    public void employeeSignUp(EmployeeDTO dto) {
        // Create employee
        Employee employee = this.employeeService.create(dto);

        // Create account
        EmployeeAccount account = this.employeeAccountService.create(dto);

        // Create hasAccount
        HasAccount hasAccount = this.hasAccountService.create(employee, account);

    }
}
