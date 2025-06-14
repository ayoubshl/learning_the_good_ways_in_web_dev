package org.gestion.backendspringboot.service;

import org.gestion.backendspringboot.model.Employee;
import org.gestion.backendspringboot.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long matricule) {

        return employeeRepository.findById(matricule);
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long matricule) {

        employeeRepository.deleteById(matricule);
    }
}