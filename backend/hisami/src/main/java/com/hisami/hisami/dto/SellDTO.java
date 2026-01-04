package com.hisami.hisami.dto;

import java.time.LocalDateTime;

public class SellDTO {
    String cpf;
    String barcode;
    LocalDateTime dateTime;
    Integer quantity;

    public SellDTO() {
    }

    public SellDTO(String cpf, String barcode, LocalDateTime dateTime) {
        this.cpf = cpf;
        this.barcode = barcode;
        this.dateTime = dateTime;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
