package com.hisami.hisami.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para SERIAL no Postgres, pode usar IDENTITY
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "description", length = 512, nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "cust", nullable = false)
    private Double cust;

    @Column(name = "percent_cust_price", nullable = false)
    private Double percentCustPrice;

    @Column(name = "barcode", length = 32, nullable = false, unique = true)
    private String barcode;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductContainRaw> raws = new HashSet<>();

    public Product() {
    }

    public Product(String name, String description, Double price, Double cust, Double percentCustPrice,
            String barcode, byte[] image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.cust = cust;
        this.percentCustPrice = percentCustPrice;
        this.barcode = barcode;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCust() {
        return cust;
    }

    public void setCust(Double cust) {
        this.cust = cust;
    }

    public Double getPercentCustPrice() {
        return percentCustPrice;
    }

    public void setPercentCustPrice(Double percentCustPrice) {
        this.percentCustPrice = percentCustPrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Set<ProductContainRaw> getRaws() {
        return raws;
    }

    public void setRaws(Set<ProductContainRaw> raws) {
        this.raws = raws;
    }

    public void addRaw(ProductContainRaw raw) {
        this.raws.add(raw);
    }

    public void removeRaw(ProductContainRaw raw) {
        this.raws.remove(raw);
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
