package com.hisami.hisami.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "HAS_ACCOUNT")
public class HasAccount {

    @EmbeddedId
    private HasAccountId id;

    @MapsId("employeeCpf")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_cpf", nullable = false)
    private Employee employee;

    @MapsId("accountUsername")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_username", nullable = false)
    private EmployeeAccount account;

    public HasAccount() {
    }

    public HasAccount(Employee employee, EmployeeAccount account) {
        this.id = new HasAccountId(employee.getCpf(), account.getUsername());
        this.employee = employee;
        this.account = account;
    }

    public HasAccountId getId() {
        return id;
    }

    public void setId(HasAccountId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeAccount getAccount() {
        return account;
    }

    public void setAccount(EmployeeAccount account) {
        this.account = account;
    }
}
