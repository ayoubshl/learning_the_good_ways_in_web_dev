package repositoryHandler;

import models.employee;
import repository.employeeRepository;

import java.util.ArrayList;
import java.util.List;

public class arrayListEmployeHandler implements employeeRepository {
    private final List<employee> employees = new ArrayList<>();
    private int nextId = 1; // To manage auto-increment for the ID field

    @Override
    public boolean addEmployee(employee emp) {
        emp.setEmployee_id(nextId++); // Assign and increment ID
        return employees.add(emp);
    }

    @Override
    public boolean removeEmployee(employee emp) {
        return employees.removeIf(e -> e.getEmployee_id() == emp.getEmployee_id());
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

    @Override
    public List<employee> getAllEmployees() {
        return new ArrayList<>(employees); // Return a copy of the list to avoid external modifications
    }
}
