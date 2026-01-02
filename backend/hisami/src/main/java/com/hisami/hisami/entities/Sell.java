package com.hisami.hisami.entities;

import java.time.LocalDateTime;

import com.hisami.hisami.entities.Id.SellId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "sell")
public class Sell {

    @EmbeddedId
    private SellId id;

    @MapsId("employeeCpf") // nome do campo dentro de SellId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_cpf", referencedColumnName = "cpf")
    private Employee employee;

    @MapsId("productId") // nome do campo dentro de SellId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Sell() {
    }

    public Sell(Employee employee, Product product, LocalDateTime dateTime) {
        this.employee = employee;
        this.product = product;
        this.id = new SellId(employee.getCpf(), product.getId(), dateTime);
    }

    public SellId getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(SellId id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // date_time já está dentro do id (SellId)
    // se quiser acessar direto, pode criar um getter:
    public LocalDateTime getDateTime() {
        return id != null ? id.getDateTime() : null;
    }
}
