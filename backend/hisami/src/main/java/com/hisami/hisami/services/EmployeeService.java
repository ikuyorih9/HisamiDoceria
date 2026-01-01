package com.hisami.hisami.services;

import java.util.List;
import java.util.Optional;

import com.hisami.hisami.dto.EmployeeDTO;
import com.hisami.hisami.entities.Employee;
import com.hisami.hisami.interfaces.EntityInterface;

public interface EmployeeService extends EntityInterface<Employee, EmployeeDTO, String> {

}
