package com.hisami.hisami.dto;

import com.hisami.hisami.entities.Employee;

public class EmployeeAccountDTO {
    private String username;
    private String email;
    private String password;
    private String role;
    private Employee employee;

    public EmployeeAccountDTO(String email, String password, String role, String username, Employee employee) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
