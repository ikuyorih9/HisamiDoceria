package com.hisami.hisami.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class HasAccountId implements Serializable {
    @Column(name = "employee_cpf", nullable = false, length = 20)
    private String employeeCpf;

    @Column(name = "account_username", nullable = false, length = 50)
    private String accountUsername;

    public HasAccountId() {
    }

    public HasAccountId(String employeeCpf, String accountUsername) {
        this.employeeCpf = employeeCpf;
        this.accountUsername = accountUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof HasAccountId that))
            return false;
        return Objects.equals(employeeCpf, that.employeeCpf) &&
                Objects.equals(accountUsername, that.accountUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeCpf, accountUsername);
    }

    public String getEmployeeCpf() {
        return employeeCpf;
    }

    public void setEmployeeCpf(String employeeCpf) {
        this.employeeCpf = employeeCpf;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }
}
