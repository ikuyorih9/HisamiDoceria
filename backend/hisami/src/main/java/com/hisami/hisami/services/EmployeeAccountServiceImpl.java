package com.hisami.hisami.services;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hisami.hisami.dto.EmployeeDTO;
import com.hisami.hisami.entities.EmployeeAccount;
import com.hisami.hisami.entities.EmployeeRole;
import com.hisami.hisami.exception.EntityAlreadyExistsException;
import com.hisami.hisami.exception.NotFoundException;
import com.hisami.hisami.repositories.EmployeeAccountRepository;

@Service
public class EmployeeAccountServiceImpl implements EmployeeAccountService {

    private final EmployeeAccountRepository employeeAccountRepository;
    private final EmployeeRoleService employeeRoleService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeAccountServiceImpl(EmployeeAccountRepository employeeAccountRepository,
            EmployeeRoleService employeeRoleService, PasswordEncoder passwordEncoder) {
        this.employeeAccountRepository = employeeAccountRepository;
        this.employeeRoleService = employeeRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeAccount create(EmployeeDTO dto) {
        // Verify account conflict
        if (this.exists(dto.getUsername())) {
            throw new EntityAlreadyExistsException("Essa conta já está cadastrada.");
        }

        // Verify role existance
        EmployeeRole role = this.employeeRoleService.find(dto.getRole())
                .orElseThrow(() -> new NotFoundException("O cargo " + dto.getRole() + " não foi encontrado."));

        // Create account
        EmployeeAccount account = new EmployeeAccount(dto.getUsername(), dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()), role,
                new Date());
        return this.employeeAccountRepository.save(account);
    }

    @Override
    public boolean exists(String username) {
        return this.employeeAccountRepository.findByUsername(username).isPresent();
    }

}
