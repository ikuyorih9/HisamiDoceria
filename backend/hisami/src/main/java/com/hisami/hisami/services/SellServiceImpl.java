package com.hisami.hisami.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hisami.hisami.dto.SellDTO;
import com.hisami.hisami.entities.Employee;
import com.hisami.hisami.entities.Id.SellId;
import com.hisami.hisami.entities.Product;
import com.hisami.hisami.entities.Sell;
import com.hisami.hisami.exception.NotFoundException;
import com.hisami.hisami.repositories.SellRepository;

import jakarta.transaction.Transactional;

@Service
public class SellServiceImpl implements SellService {

    private final SellRepository sellRepository;
    private final EmployeeService employeeService;
    private final ProductService productService;

    public SellServiceImpl(SellRepository sellRepository, EmployeeService employeeService,
            ProductService productService) {
        this.sellRepository = sellRepository;
        this.employeeService = employeeService;
        this.productService = productService;
    }

    @Override
    @Transactional
    public Sell create(SellDTO object) {
        // Verifies employee is registred
        Employee employee = this.employeeService.find(object.getCpf())
                .orElseThrow(() -> new NotFoundException("Empregado não registrado."));

        Product product = this.productService.find(object.getBarcode())
                .orElseThrow(() -> new NotFoundException("Produto não registrado."));

        Sell sell = new Sell(employee, product, LocalDateTime.now(), object.getQuantity());
        return this.sellRepository.save(sell);
    }

    @Override
    public Optional<Sell> find(SellId id) {
        return this.sellRepository.findById(id);
    }

    @Override
    public boolean exists(SellId id) {
        return this.find(id).isPresent();
    }

    @Override
    public List<Sell> list() {
        return this.sellRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(SellId id) {
        Sell sell = this.find(id).orElseThrow(() -> new NotFoundException("Venda não registrada."));

        sellRepository.delete(sell);
    }

    @Override
    public List<Sell> getSellByProduct(String barcode) {
        return this.sellRepository.findAllByProductBarcode(barcode);
    }

    @Override
    public Sell edit(SellId id, SellDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }

}
