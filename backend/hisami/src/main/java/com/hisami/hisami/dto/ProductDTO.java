package com.hisami.hisami.dto;

import java.util.List;

import com.hisami.hisami.entities.Raw;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double cust;
    private Double percentCustPrice;
    private String barcode;
    private String image;
    private List<Raw> raws;

    public ProductDTO(Long id, String name, String description, Double price, Double cust, Double percentCustPrice,
            String barcode, String image, List<Raw> raws) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cust = cust;
        this.percentCustPrice = percentCustPrice;
        this.barcode = barcode;
        this.image = image;
        this.raws = raws;
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

    public List<Raw> getRaws() {
        return raws;
    }

    public void setRaws(List<Raw> raws) {
        this.raws = raws;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
                + ", cust=" + cust + ", percentCustPrice=" + percentCustPrice + ", barcode=" + barcode + ", image="
                + image + "]";
    }
}
