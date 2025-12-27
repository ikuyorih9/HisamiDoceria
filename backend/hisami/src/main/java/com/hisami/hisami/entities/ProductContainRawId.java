package com.hisami.hisami.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductContainRawId implements Serializable {

    @Column(name = "raw_name", length = 128)
    private String rawName;

    @Column(name = "product_id")
    private Long productId;

    public ProductContainRawId() {
    }

    public ProductContainRawId(String rawName, Long productId) {
        this.rawName = rawName;
        this.productId = productId;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ProductContainRawId that))
            return false;
        return Objects.equals(rawName, that.rawName)
                && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawName, productId);
    }

}
