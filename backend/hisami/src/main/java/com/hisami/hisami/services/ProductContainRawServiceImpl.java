package com.hisami.hisami.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hisami.hisami.entities.Product;
import com.hisami.hisami.entities.ProductContainRaw;
import com.hisami.hisami.entities.ProductContainRawId;
import com.hisami.hisami.entities.Raw;
import com.hisami.hisami.exception.CantRegisterProductException;
import com.hisami.hisami.exception.NotFoundException;
import com.hisami.hisami.repositories.ProductContainRawRepository;

import jakarta.persistence.EntityNotFoundException;

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

    @Override
    public void delete(Product p, Raw r) {
        // garante que Product e Raw existem (mesma regra do create)
        if (!this.productService.exists(p.getBarcode()))
            throw new NotFoundException("Produto não encontrado: " + p.getBarcode());

        if (!this.rawService.exists(r.getName()))
            throw new NotFoundException("Ingrediente não encontrado: " + r.getName());

        ProductContainRawId id = new ProductContainRawId(r.getName(), p.getId());

        ProductContainRaw pcr = this.productContainRawRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Relação ProductContainRaw não encontrada para product=" + p.getBarcode() +
                                " e raw=" + r.getName()));

        // remove da memória (mantém os dois lados sincronizados, caso tenha relação
        // bidirecional)
        this.productService.find(p.getBarcode()).get().removeRaw(pcr);
        this.rawService.find(r.getName()).get().removeProduct(pcr);

        this.productContainRawRepository.delete(pcr);
    }

    @Override
    public List<ProductContainRaw> findAllByRaw(String raw) {
        return this.productContainRawRepository.findAllByRaw(raw);
    }

    @Override
    public List<ProductContainRaw> findAllByProduct(String barcode) {
        return this.productContainRawRepository.findAllByProductBarcode(barcode);
    }
}
