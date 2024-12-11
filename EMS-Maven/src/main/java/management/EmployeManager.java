package management;

import models.employee;
import repository.employeeRepository;

public class EmployeManager {
    private final employeeRepository repository;

    public EmployeManager(employeeRepository repository) {
        this.repository = repository;
    }

    public boolean addEmployee(employee emp) {
        return repository.addEmployee(emp);
    }

    public boolean removeEmployee(employee emp) {
        return repository.removeEmployee(emp);
    }

    public employee findEmployeeById(int id) {
        return repository.findEmployeeById(id);
    }

    public employee editEmployee(employee emp) {
        return repository.updateEmployee(emp);
    }
}
