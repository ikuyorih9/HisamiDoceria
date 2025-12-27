package com.hisami.hisami.services;

import com.hisami.hisami.entities.Product;
import com.hisami.hisami.entities.ProductContainRaw;
import com.hisami.hisami.entities.ProductContainRawId;
import com.hisami.hisami.entities.Raw;
import com.hisami.hisami.interfaces.EntityInterface;

public interface ProductContainRawService {
    public ProductContainRaw create(Product p, Raw r, int quantity);
}
