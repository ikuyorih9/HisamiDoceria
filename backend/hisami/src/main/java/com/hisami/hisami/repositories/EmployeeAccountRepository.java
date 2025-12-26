package com.hisami.hisami.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hisami.hisami.entities.EmployeeAccount;

@Repository
public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, String>{
    public Optional<EmployeeAccount> findByUsername(String username);
}
