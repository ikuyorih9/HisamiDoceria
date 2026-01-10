package com.hisami.hisami.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hisami.hisami.dto.ApiResponse;
import com.hisami.hisami.dto.RawDTO;
import com.hisami.hisami.dto.RegisteringDTO;
import com.hisami.hisami.dto.SellDTO;
import com.hisami.hisami.entities.Product;
import com.hisami.hisami.entities.Raw;
import com.hisami.hisami.entities.Sell;
import com.hisami.hisami.services.ProductService;
import com.hisami.hisami.services.RawService;
import com.hisami.hisami.services.RegisterService;
import com.hisami.hisami.services.SellService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final RegisterService registerService;
    private final ProductService productService;
    private final RawService rawService;
    private final SellService sellService;

    public ApiController(RegisterService registerService, ProductService productService, RawService rawService,
            SellService sellService) {
        this.registerService = registerService;
        this.productService = productService;
        this.rawService = rawService;
        this.sellService = sellService;
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return this.productService.list();
    }

    @PostMapping("/product")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody RegisteringDTO registeringDTO) {
        this.registerService.registerProduct(registeringDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(HttpStatus.CREATED.value(), "Produto foi cadastrado com sucesso"));
    }

    @DeleteMapping("/product/{barcode}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String barcode) {
        // DELETE ALL SELLS
        List<Sell> sells = this.sellService.getSellByProduct(barcode);
        for (Sell sell : sells) {
            this.sellService.delete(sell.getId()); // ou sell.getSellId(), conforme o nome do campo
        }

        // DELETE PRODUCT
        this.registerService.deleteProduct(barcode);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Produto foi deletado com sucesso"));
    }

    @PostMapping("/product/{barcode}")
    public ResponseEntity<ApiResponse> editProduct(@PathVariable String barcode, @RequestBody RegisteringDTO dto) {
        this.registerService.editProduct(barcode, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Produto foi editado com sucesso"));
    }

    // -------------> RAW <----------------

    @GetMapping("/raw")
    public List<Raw> getAllRaws() {
        return this.rawService.list();
    }

    @PostMapping("/raw")
    public ResponseEntity<ApiResponse> createRaw(@RequestBody RawDTO dto) {
        this.rawService.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(HttpStatus.CREATED.value(), "Ingrediente foi cadastrado com sucesso"));
    }

    @DeleteMapping("/raw/{name}")
    public ResponseEntity<ApiResponse> deleteRaw(@PathVariable String name) {
        this.registerService.deleteRaw(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Ingrediente foi deletado com sucesso"));
    }

    @PostMapping("/raw/{name}")
    public ResponseEntity<ApiResponse> editRaw(@PathVariable String name, @RequestBody RawDTO dto) {
        this.rawService.edit(name, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Ingrediente modificado"));
    }

    // ------------> SELL <-------------

    @GetMapping("/sell")
    public List<Sell> getSells() {
        return this.sellService.list();
    }

    @PostMapping("/sell")
    public ResponseEntity<ApiResponse> sellProduct(@RequestBody SellDTO dto) {
        this.sellService.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(HttpStatus.CREATED.value(), "Venda registrada."));
    }
}
