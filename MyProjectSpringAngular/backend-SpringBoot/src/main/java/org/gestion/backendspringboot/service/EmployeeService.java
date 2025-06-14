package org.gestion.backendspringboot.service;

import org.gestion.backendspringboot.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface EmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long matricule);
    Employee saveEmployee(Employee employee);
    void deleteEmployee(long matricule);
}