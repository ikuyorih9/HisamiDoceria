package com.hisami.hisami.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hisami.hisami.dto.RawDTO;
import com.hisami.hisami.dto.RegisteringDTO;
import com.hisami.hisami.entities.Product;
import com.hisami.hisami.entities.Raw;
import com.hisami.hisami.services.ProductService;
import com.hisami.hisami.services.RawService;
import com.hisami.hisami.services.RegisterService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final RegisterService registerService;
    private final ProductService productService;
    private final RawService rawService;

    public ApiController(RegisterService registerService, ProductService productService, RawService rawService) {
        this.registerService = registerService;
        this.productService = productService;
        this.rawService = rawService;
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return this.productService.list();
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody RegisteringDTO registeringDTO) {
        this.registerService.registerProduct(registeringDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto foi cadastrado com sucesso");
    }

    @GetMapping("/raw")
    public List<Raw> getAllRaws() {
        return this.rawService.list();
    }

    @PostMapping("/raw")
    public ResponseEntity<String> createRaw(@RequestBody RawDTO dto) {
        this.rawService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ingrediente foi cadastrado com sucesso");
    }
}
