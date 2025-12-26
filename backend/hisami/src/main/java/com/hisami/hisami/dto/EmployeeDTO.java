package com.hisami.hisami.dto;

public class EmployeeDTO {
    private String name;
    private String cpf;
    private Double salary;
    private String username;
    private String email;
    private String password;
    private String role;

    public EmployeeDTO(String name, String cpf, Double salary, String username, String email, String password,
            String role) {
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
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

    @Override
    public String toString() {
        return "EmployeeDTO [name=" + name + ", cpf=" + cpf + ", salary=" + salary + ", username=" + username
                + ", email=" + email + ", password=" + password + ", role=" + role + "]";
    }

}
