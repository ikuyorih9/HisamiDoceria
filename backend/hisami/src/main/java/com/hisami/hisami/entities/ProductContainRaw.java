package com.hisami.hisami.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_contain_raw")
public class ProductContainRaw {
    @EmbeddedId
    private ProductContainRawId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rawName")
    @JoinColumn(name = "raw_name", nullable = false)
    private Raw raw;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public ProductContainRaw() {
    }

    public ProductContainRaw(Product product, Raw raw, Integer quantity) {
        this.id = new ProductContainRawId(raw.getName(), product.getId());
        this.raw = raw;
        this.product = product;
        this.quantity = quantity;
    }

    public ProductContainRaw(ProductContainRawId id, Raw raw, Product product, Integer quantity) {
        this.id = id;
        this.raw = raw;
        this.product = product;
        this.quantity = quantity;
    }

    public ProductContainRawId getId() {
        return id;
    }

    public void setId(ProductContainRawId id) {
        this.id = id;
    }

    public Raw getRaw() {
        return raw;
    }

    public void setRaw(Raw raw) {
        this.raw = raw;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
