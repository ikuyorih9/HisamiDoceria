package com.hisami.hisami.services;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hisami.hisami.dto.ProductDTO;
import com.hisami.hisami.entities.Product;
import com.hisami.hisami.exception.EntityAlreadyExistsException;
import com.hisami.hisami.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(ProductDTO object) {
        // Verifify whether product already exists.
        if (object.getBarcode() != null &&
                this.exists(object.getBarcode()))
            throw new EntityAlreadyExistsException("Produto já foi cadastrado.");

        // Verify whether object has an image
        if (object.getImage() != null) {
            System.out.println("Getting base64... " + object.getImage());
            byte[] bytes = Base64.getDecoder().decode(object.getImage());
            System.out.println("IMAGE: " + bytes);
            Product product = new Product(object.getName(), object.getDescription(), object.getPrice(),
                    object.getCust(), object.getPercentCustPrice(), object.getBarcode(), bytes);
            return productRepository.save(product);
        }
        Product product = new Product(object.getName(), object.getDescription(), object.getPrice(),
                object.getCust(), object.getPercentCustPrice(), object.getBarcode(), null);

        return productRepository.save(product);
    }

    @Override
    public boolean exists(String barcode) {
        return this.productRepository.findByBarcode(barcode).isPresent();
    }

    @Override
    public Optional<Product> find(String id) {
        return this.productRepository.findByBarcode(id);
    }

    @Override
    public List<Product> list() {
        return this.productRepository.findAll();
    }

}
