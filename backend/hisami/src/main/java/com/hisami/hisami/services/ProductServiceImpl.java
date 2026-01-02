package com.hisami.hisami.services;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hisami.hisami.dto.ProductDTO;
import com.hisami.hisami.entities.Product;
import com.hisami.hisami.exception.EntityAlreadyExistsException;
import com.hisami.hisami.exception.NotFoundException;
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
            byte[] bytes = Base64.getDecoder().decode(object.getImage());
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

    @Override
    public void delete(String barcode) {
        Product product = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException(
                        "Produto não foi registrado"));
        this.productRepository.delete(product);
    }

    @Override
    public Product edit(String barcode, ProductDTO dto) {

        Product product = this.find(barcode).orElseThrow(() -> new NotFoundException("Produto não registrado."));
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }

        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }

        if (dto.getCust() != null) {
            product.setCust(dto.getCust());
        }

        if (dto.getPercentCustPrice() != null) {
            product.setPercentCustPrice(dto.getPercentCustPrice());
        }

        if (dto.getBarcode() != null) {
            product.setBarcode(dto.getBarcode());
        }

        if (dto.getImage() != null) {
            String img = dto.getImage();

            // se vier como data URL: "data:image/png;base64,AAAA"
            if (img.startsWith("data:")) {
                img = img.substring(img.indexOf(",") + 1);
            }

            byte[] bytes = Base64.getDecoder().decode(img);
            product.setImage(bytes);
        }

        if (dto.getStockQuantity() != null) {
            product.setStockQuantity(dto.getStockQuantity());
        }

        return product;
    }

}
