package com.hisami.hisami.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hisami.hisami.dto.EmployeeDTO;
import com.hisami.hisami.services.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("signup")
    public ResponseEntity cadastrarUsuario(@RequestBody EmployeeDTO employee) {
        System.out.println("[CADASTRO]: cadastrando " + employee.toString() + "... (auth/cadastrar)");

        this.authService.employeeSignUp(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}
