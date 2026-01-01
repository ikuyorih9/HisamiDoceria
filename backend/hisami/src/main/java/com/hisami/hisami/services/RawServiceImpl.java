package com.hisami.hisami.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hisami.hisami.dto.RawDTO;
import com.hisami.hisami.entities.Raw;
import com.hisami.hisami.exception.EntityAlreadyExistsException;
import com.hisami.hisami.exception.NotFoundException;
import com.hisami.hisami.repositories.RawRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RawServiceImpl implements RawService {

    private final RawRepository rawRepository;

    public RawServiceImpl(RawRepository rawRepository) {
        this.rawRepository = rawRepository;
    }

    @Override
    public Raw create(RawDTO dto) {
        // Verifies whether raw is already saved.
        if (this.exists(dto.getName())) {
            throw new EntityAlreadyExistsException("Ingrediente já foi cadastrado.");
        }

        Raw raw = new Raw(dto.getName(), dto.getCust());
        return rawRepository.save(raw);
    }

    @Override
    public boolean exists(String name) {
        return this.rawRepository.findById(name).isPresent();
    }

    @Override
    public Optional<Raw> find(String id) {
        return this.rawRepository.findById(id);
    }

    @Override
    public List<Raw> list() {
        return this.rawRepository.findAll();
    }

    @Override
    public void delete(String name) {
        Raw raw = rawRepository.findById(name)
                .orElseThrow(() -> new NotFoundException("Ingrediente não registrado."));

        rawRepository.delete(raw);
    }
}
