package repositoryHandler;

import models.employee;
import repository.employeeRepository;

import java.util.ArrayList;
import java.util.List;

public class arrayListEmployeHandler implements employeeRepository {
    private final List<employee> employees = new ArrayList<>();

    @Override
    public boolean addEmployee(employee emp) {
        return employees.add(emp);
    }

    @Override
    public boolean removeEmployee(employee emp) {
        return employees.remove(emp);
    }

    @Override
    public employee findEmployeeById(int id) {
        return employees.stream()
                .filter(emp -> emp.getEmployee_id() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public employee updateEmployee(employee emp) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployee_id() == emp.getEmployee_id()) {
                employees.set(i, emp); // Update the existing employee
                return emp;
            }
        }
        return null; // If the employee is not found, return null
    }
}
