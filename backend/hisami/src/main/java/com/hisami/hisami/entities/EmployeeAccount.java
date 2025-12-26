package com.hisami.hisami.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class EmployeeAccount {
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "init_date", nullable = false)
    private Date initDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role", nullable = false)
    private EmployeeRole role;

    @OneToMany(mappedBy = "account")
    private Set<HasAccount> employees = new HashSet<>();

    public EmployeeAccount() {
    }

    public EmployeeAccount(String username, String email, String password, EmployeeRole role,
            Set<HasAccount> employees) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.employees = employees;
    }

    public EmployeeAccount(String username, String email, String password, EmployeeRole role, Date initDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.initDate = initDate;
        this.endDate = null;
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

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public void addEmployee(HasAccount employee) {
        this.employees.add(employee);
    }

    public Set<HasAccount> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<HasAccount> employees) {
        this.employees = employees;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // getters/setters
}
