package com.hisami.hisami.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hisami.hisami.entities.Employee;
import com.hisami.hisami.entities.EmployeeRole;
import com.hisami.hisami.entities.HasAccount;
import com.hisami.hisami.exception.NotFoundException;
import com.hisami.hisami.services.EmployeeAccountService;
import com.hisami.hisami.services.EmployeeService;
import com.hisami.hisami.services.HasAccountService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final HasAccountService hasAccountService;
    private final EmployeeAccountService employeeAccountService;

    public EmployeeController(EmployeeService employeeService, HasAccountService hasAccountService,
            EmployeeAccountService employeeAccountService) {
        this.employeeService = employeeService;
        this.hasAccountService = hasAccountService;
        this.employeeAccountService = employeeAccountService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.list();
    }

    @GetMapping("/{username}")
    public Employee getEmployee(@PathVariable String username) {
        HasAccount hasAccount = this.hasAccountService.findByAccount(username);
        return this.employeeService.find(hasAccount.getEmployee().getCpf())
                .orElseThrow(() -> new NotFoundException("Empregado não foi encontrado"));
    }

    @GetMapping("/role/{username}")
    public EmployeeRole getEmployeeRol(@PathVariable String username) {
        return this.employeeAccountService.findEmployeeRole(username);

    }

}
