package com.hisami.hisami.dto;

import java.util.List;

public class RegisteringDTO {
    private ProductDTO product;
    private List<RawQuantityDTO> raws;

    public RegisteringDTO(ProductDTO product, List<RawQuantityDTO> raws) {
        this.product = product;
        this.raws = raws;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public List<RawQuantityDTO> getRaws() {
        return raws;
    }

    public void setRaws(List<RawQuantityDTO> raws) {
        this.raws = raws;
    }

    public void addRaws(RawQuantityDTO raws) {
        this.raws.add(raws);
    }

    @Override
    public String toString() {
        return "RegisteringDTO [product=" + product + ", raws=" + raws + "]";
    }

}
