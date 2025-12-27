package com.hisami.hisami.services;

import org.springframework.stereotype.Service;

import com.hisami.hisami.entities.Product;
import com.hisami.hisami.entities.ProductContainRaw;
import com.hisami.hisami.entities.ProductContainRawId;
import com.hisami.hisami.entities.Raw;
import com.hisami.hisami.exception.CantRegisterProductException;
import com.hisami.hisami.repositories.ProductContainRawRepository;

@Service
public class ProductContainRawServiceImpl implements ProductContainRawService {
    private final ProductContainRawRepository productContainRawRepository;
    private final ProductService productService;
    private final RawService rawService;

    public ProductContainRawServiceImpl(ProductContainRawRepository productContainRawRepository,
            ProductService productService, RawService rawService) {
        this.productContainRawRepository = productContainRawRepository;
        this.productService = productService;
        this.rawService = rawService;
    }

    @Override
    public ProductContainRaw create(Product p, Raw r, int quantity) {
        if (!this.productService.exists(p.getBarcode()))
            throw new CantRegisterProductException("Produto não foi registrado.");

        if (!this.rawService.exists(r.getName()))
            throw new CantRegisterProductException("Ingrediente não foi registrado.");

        ProductContainRaw pcr = new ProductContainRaw(p, r, quantity);
        this.productService.find(p.getBarcode()).get().addRaw(pcr);
        this.rawService.find(r.getName()).get().addProduct(pcr);

        return this.productContainRawRepository.save(pcr);

    }

}
