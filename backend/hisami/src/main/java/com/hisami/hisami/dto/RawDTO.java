package com.hisami.hisami.dto;

public class RawDTO {
    String name;
    Double cust;
    Integer stockQuantity;

    public RawDTO() {
    }

    public RawDTO(String name, Double cust, Integer stockQuantity) {
        this.name = name;
        this.cust = cust;
        this.stockQuantity = stockQuantity;
    }

    public RawDTO(String name, Double cust) {
        this.name = name;
        this.cust = cust;
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

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
