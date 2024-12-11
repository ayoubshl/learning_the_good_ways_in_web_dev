package repository;

import models.employee;

public interface employeeRepository {
    boolean addEmployee(employee emp);            // Add a new employee
    boolean removeEmployee(employee emp);         // Remove an existing employee
    employee findEmployeeById(int id);            // Find an employee by their ID
    employee updateEmployee(employee emp);        // Update an existing employee
}
