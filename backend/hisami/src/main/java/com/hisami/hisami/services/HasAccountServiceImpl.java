package com.hisami.hisami.services;

import org.springframework.stereotype.Service;

import com.hisami.hisami.entities.Employee;
import com.hisami.hisami.entities.EmployeeAccount;
import com.hisami.hisami.entities.HasAccount;
import com.hisami.hisami.repositories.HasAccountRepository;

@Service
public class HasAccountServiceImpl implements HasAccountService {
    private final HasAccountRepository hasAccountRepository;

    public HasAccountServiceImpl(HasAccountRepository hasAccountRepository) {
        this.hasAccountRepository = hasAccountRepository;
    }

    @Override
    public HasAccount create(Employee employee, EmployeeAccount account) {
        // Create connection
        HasAccount hasAccount = new HasAccount(employee, account);

        // Connect Employe to Account
        employee.addAccount(hasAccount);
        account.addEmployee(hasAccount);

        return hasAccountRepository.save(hasAccount);
    }

}
