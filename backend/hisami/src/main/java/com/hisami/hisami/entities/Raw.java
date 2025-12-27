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
@Table(name = "raw")
public class Raw {
    @Id
    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "cust")
    private Double cust;

    @OneToMany(mappedBy = "raw")
    @JsonIgnore
    private Set<ProductContainRaw> products = new HashSet<>();

    public Raw() {
    }

    public Raw(String name, Double cust) {
        this.name = name;
        this.cust = cust;
    }

    public Raw(String name, Double cust, Set<ProductContainRaw> products) {
        this.name = name;
        this.cust = cust;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCust() {
        return cust;
    }

    public void setCust(Double cust) {
        this.cust = cust;
    }

    public Set<ProductContainRaw> getProducts() {
        return products;
    }

    public void addProduct(ProductContainRaw pcr) {
        this.products.add(pcr);
    }

    public void setProducts(Set<ProductContainRaw> products) {
        this.products = products;
    }
}
