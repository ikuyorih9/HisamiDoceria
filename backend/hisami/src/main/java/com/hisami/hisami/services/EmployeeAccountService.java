package com.hisami.hisami.services;

import com.hisami.hisami.dto.EmployeeDTO;
import com.hisami.hisami.entities.EmployeeAccount;

public interface EmployeeAccountService {
    public EmployeeAccount create(EmployeeDTO dto);

    public boolean exists(String username);
}
