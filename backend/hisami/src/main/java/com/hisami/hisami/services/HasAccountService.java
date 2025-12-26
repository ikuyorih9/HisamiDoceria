package com.hisami.hisami.services;

import com.hisami.hisami.entities.Employee;
import com.hisami.hisami.entities.EmployeeAccount;
import com.hisami.hisami.entities.HasAccount;

public interface HasAccountService {
    public HasAccount create(Employee employee, EmployeeAccount account);
}
