package com.hisami.hisami.dto;

public class RawQuantityDTO {
    private RawDTO raw;
    private Integer quantity;

    public RawQuantityDTO() {
    }

    public RawQuantityDTO(Integer quantity, RawDTO raw) {
        this.quantity = quantity;
        this.raw = raw;
    }

    public RawDTO getRaw() {
        return raw;
    }

    public void setRaw(RawDTO raw) {
        this.raw = raw;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
