package com.hisami.hisami.config;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hisami.hisami.entities.EmployeeAccount;
import com.hisami.hisami.repositories.EmployeeAccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeAccountRepository employeeAccountRepository;

    public CustomUserDetailsService(EmployeeAccountRepository employeeAccountRepository) {
        this.employeeAccountRepository = employeeAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeAccount account = employeeAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        System.out.println("[LOAD USER BY USERNAME] " + account.getUsername() + " " + account.getPassword());
        return new User(
                account.getUsername(),
                account.getPassword(), // Deve estar criptografada com BCrypt
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
