package com.hisami.hisami.services;

import com.hisami.hisami.dto.EmployeeDTO;
import com.hisami.hisami.entities.EmployeeAccount;
import com.hisami.hisami.entities.EmployeeRole;
import com.hisami.hisami.interfaces.EntityInterface;

public interface EmployeeAccountService extends EntityInterface<EmployeeAccount, EmployeeDTO, String> {

    public EmployeeRole findEmployeeRole(String username);
}
