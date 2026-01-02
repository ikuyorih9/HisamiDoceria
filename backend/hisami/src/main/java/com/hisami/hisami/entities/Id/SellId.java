package com.hisami.hisami.entities.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SellId implements Serializable {

    @Column(name = "employee_cpf")
    private String employeeCpf;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public SellId() {
    }

    public SellId(String employeeCpf, Long productId, LocalDateTime dateTime) {
        this.employeeCpf = employeeCpf;
        this.productId = productId;
        this.dateTime = dateTime;
    }

    public String getEmployeeCpf() {
        return employeeCpf;
    }

    public Long getProductId() {
        return productId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setEmployeeCpf(String employeeCpf) {
        this.employeeCpf = employeeCpf;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SellId))
            return false;
        SellId sellId = (SellId) o;
        return Objects.equals(employeeCpf, sellId.employeeCpf)
                && Objects.equals(productId, sellId.productId)
                && Objects.equals(dateTime, sellId.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeCpf, productId, dateTime);
    }
}
