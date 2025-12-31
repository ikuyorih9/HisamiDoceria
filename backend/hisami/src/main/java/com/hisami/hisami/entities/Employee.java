package com.hisami.hisami.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "salary")
    private Double salary;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    private Set<HasAccount> accounts = new HashSet<>();

    public Employee() {
        super();
    }

    public Employee(String cpf, String name, Double salary) {
        super();
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Set<HasAccount> getAccounts() {
        return accounts;
    }

    public void addAccount(HasAccount account) {
        this.accounts.add(account);
    }

    public void setAccounts(Set<HasAccount> accounts) {
        this.accounts = accounts;
    }
}
