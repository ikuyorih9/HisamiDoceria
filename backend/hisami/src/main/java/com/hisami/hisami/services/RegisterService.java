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
    public void editProduct(String barcode, RegisteringDTO dto) {
        Product product;
        if (dto.getProduct() != null) {
            product = this.productService.edit(barcode, dto.getProduct());
        } else {
            product = this.productService.find(barcode)
                    .orElseThrow(() -> new NotFoundException("Produto não registrado."));
        }

        // Update raws
        if (dto.getRaws() != null) {
            System.out.println("DELETING RAWS");
            // Clean all raws connected to product
            List<ProductContainRaw> pcrs = this.pcrService.findAllByProduct(barcode);
            System.out.println("ACTUAL RAWS: " + pcrs.toString());
            for (ProductContainRaw pcr : pcrs) {
                this.pcrService.delete(pcr.getProduct(), pcr.getRaw());
            }

            System.out.println("CREATING NEW RAWS");
            // Create new raws
            for (RawQuantityDTO rdto : dto.getRaws()) {
                Raw raw = this.rawService.find(rdto.getRaw().getName()).orElseThrow(
                        () -> new NotFoundException("Ingrediente " + rdto.getRaw().getName() + " não foi encontrado"));

                this.pcrService.create(product, raw, rdto.getQuantity());
            }
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

    private Product NotFoundException(String produto_não_registrado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
