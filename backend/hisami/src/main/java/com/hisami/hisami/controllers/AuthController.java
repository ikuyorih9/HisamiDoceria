package com.hisami.hisami.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        if (authentication == null)
            return Map.of("authenticated", false);

        return Map.of(
                "authenticated", authentication.isAuthenticated(),
                "name", authentication.getName(),
                "authorities", authentication.getAuthorities().stream()
                        .map(a -> a.getAuthority()).toList());
    }

    @PostMapping("signup")
    public ResponseEntity cadastrarUsuario(@RequestBody EmployeeDTO employee) {
        System.out.println("[CADASTRO]: cadastrando " + employee.toString() + "... (auth/cadastrar)");

        this.authService.employeeSignUp(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}
