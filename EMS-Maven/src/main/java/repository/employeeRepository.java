package repository;

import models.employee;
import java.util.List;

public interface employeeRepository {
    boolean addEmployee(employee emp);            // Add a new employee
    boolean removeEmployee(employee emp);         // Remove an existing employee
    employee findEmployeeById(int id);            // Find an employee by their ID
    employee updateEmployee(employee emp);        // Update an existing employee
    List<employee> getAllEmployees();             // Get a list of all employees
}
