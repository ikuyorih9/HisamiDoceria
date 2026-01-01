package com.hisami.hisami.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hisami.hisami.dto.RawQuantityDTO;
import com.hisami.hisami.dto.RegisteringDTO;
import com.hisami.hisami.entities.Product;
import com.hisami.hisami.entities.ProductContainRaw;
import com.hisami.hisami.entities.Raw;
import com.hisami.hisami.exception.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class RegisterService {
    private final ProductService productService;
    private final RawService rawService;
    private final ProductContainRawService pcrService;

    public RegisterService(ProductService productService, RawService rawService, ProductContainRawService pcrService) {
        this.productService = productService;
        this.rawService = rawService;
        this.pcrService = pcrService;
    }

    @Transactional
    public void registerProduct(RegisteringDTO dto) {
        // Create product
        Product product = productService.create(dto.getProduct());

        for (RawQuantityDTO rdto : dto.getRaws()) {
            Raw raw = this.rawService.find(rdto.getRaw().getName()).orElseThrow(
                    () -> new NotFoundException("Ingrediente " + rdto.getRaw().getName() + " não foi encontrado"));

            this.pcrService.create(product, raw, rdto.getQuantity());
        }

    }

    @Transactional
    public void deleteRaw(String raw) {
        // Remove every ProductContainRaw
        List<ProductContainRaw> pcrs = this.pcrService.findAllByRaw(raw);
        for (ProductContainRaw pcr : pcrs) {
            this.pcrService.delete(pcr.getProduct(), pcr.getRaw());
        }

        // Remove every Product

        this.rawService.delete(raw);

    }

    @Transactional
    public void deleteProduct(String barcode) {
        // Remove every ProductContainRaw
        List<ProductContainRaw> pcrs = this.pcrService.findAllByProduct(barcode);
        for (ProductContainRaw pcr : pcrs) {
            this.pcrService.delete(pcr.getProduct(), pcr.getRaw());
        }

        this.productService.delete(barcode);
    }
}
