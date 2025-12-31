package com.hisami.hisami.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "employee_role")
public class EmployeeRole {
    @Id
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "can_sell", nullable = false)
    private boolean canSell = false;

    @Column(name = "can_edit_menu", nullable = false)
    private boolean canEditMenu = false;

    @Column(name = "can_change_roles", nullable = false)
    private boolean canChangeRoles = false;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<EmployeeAccount> accounts = new HashSet<>();

    public EmployeeRole() {
    }

    public EmployeeRole(String name, String description, boolean canSell, boolean canEditMenu, boolean canChangeRoles) {
        this.name = name;
        this.description = description;
        this.canSell = canSell;
        this.canEditMenu = canEditMenu;
        this.canChangeRoles = canChangeRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCanSell() {
        return canSell;
    }

    public void setCanSell(boolean canSell) {
        this.canSell = canSell;
    }

    public boolean isCanEditMenu() {
        return canEditMenu;
    }

    public void setCanEditMenu(boolean canEditMenu) {
        this.canEditMenu = canEditMenu;
    }

    public boolean isCanChangeRoles() {
        return canChangeRoles;
    }

    public void setCanChangeRoles(boolean canChangeRoles) {
        this.canChangeRoles = canChangeRoles;
    }

    public Set<EmployeeAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<EmployeeAccount> accounts) {
        this.accounts = accounts;
    }
}