package com.hisami.hisami.services;

import java.util.List;

import com.hisami.hisami.entities.Product;
import com.hisami.hisami.entities.ProductContainRaw;
import com.hisami.hisami.entities.Raw;

public interface ProductContainRawService {
    public ProductContainRaw create(Product p, Raw r, int quantity);

    public List<ProductContainRaw> findAllByRaw(String raw);

    public List<ProductContainRaw> findAllByProduct(String barcode);

    public void delete(Product p, Raw r);
}
