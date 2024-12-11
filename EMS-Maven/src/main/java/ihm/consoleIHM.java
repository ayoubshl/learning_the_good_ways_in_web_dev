package ihm;

import management.*;
import models.*;

import java.util.List;
import java.util.Scanner;

public class consoleIHM {
    private final EmployeManager employeeManager; // Employee Manager instance
    private final SalaryManager salaryManager;    // Salary Manager instance
    private final Scanner scanner;               // Scanner for user input

    public consoleIHM(EmployeManager employeeManager, SalaryManager salaryManager) {
        this.employeeManager = employeeManager;
        this.salaryManager = salaryManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Employee & Salary Management System!");
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Edit Employee");
            System.out.println("4. View Employee Details");
            System.out.println("5. List All Employees");
            System.out.println("6. Add Reward");
            System.out.println("7. Apply Penalty");
            System.out.println("8. View Total Net Salary");
            System.out.println("9. List All Salaries");
            System.out.println("10. Add Salary");
            System.out.println("11. Exit");

            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> removeEmployee();
                case 3 -> editEmployee();
                case 4 -> viewEmployeeDetails();
                case 5 -> listAllEmployees();
                case 6 -> addReward();
                case 7 -> applyPenalty();
                case 8 -> viewTotalNetSalary();
                case 9 -> listAllSalaries();
                case 10 -> addSalary();
                case 11 -> {
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addEmployee() {
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        System.out.println("Enter Phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        employee newEmployee = new employee(0, name, address, phone, email);
        boolean employeeAdded = employeeManager.addEmployee(newEmployee);

        if (employeeAdded) {
            System.out.println("Employee added successfully.");
            System.out.println("Now provide salary details for the new employee.");
            System.out.println("Enter Wage per Hour:");
            float wagePerHour = Float.parseFloat(scanner.nextLine());
            System.out.println("Enter Hours Worked:");
            int hoursWorked = Integer.parseInt(scanner.nextLine());

            salary newSalary = new salary(0, wagePerHour, hoursWorked, newEmployee);
            boolean salaryAdded = salaryManager.addSalary(newSalary);

            if (salaryAdded) {
                System.out.println("Salary record added successfully.");
            } else {
                System.out.println("Failed to add salary record.");
            }
        } else {
            System.out.println("Failed to add employee.");
        }
    }

    private void removeEmployee() {
        System.out.println("Enter Employee ID to remove:");
        int id = Integer.parseInt(scanner.nextLine());
        employee emp = employeeManager.findEmployeeById(id);

        if (emp != null) {
            boolean result = employeeManager.removeEmployee(emp);
            System.out.println(result ? "Employee removed successfully." : "Failed to remove employee.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void editEmployee() {
        System.out.println("Enter Employee ID to edit:");
        int id = Integer.parseInt(scanner.nextLine());
        employee emp = employeeManager.findEmployeeById(id);

        if (emp != null) {
            System.out.println("Enter New Address:");
            String address = scanner.nextLine();
            System.out.println("Enter New Phone:");
            String phone = scanner.nextLine();
            System.out.println("Enter New Email:");
            String email = scanner.nextLine();

            emp.setAddress(address);
            emp.setPhone(phone);
            emp.setEmail(email);

            employee updatedEmployee = employeeManager.editEmployee(emp);
            System.out.println(updatedEmployee != null ? "Employee updated successfully." : "Failed to update employee.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void viewEmployeeDetails() {
        System.out.println("Enter Employee ID to view:");
        int id = Integer.parseInt(scanner.nextLine());
        employee emp = employeeManager.findEmployeeById(id);

        if (emp != null) {
            System.out.println("Employee Details:");
            System.out.println("ID: " + emp.getEmployee_id());
            System.out.println("Name: " + emp.getName());
            System.out.println("Address: " + emp.getAddress());
            System.out.println("Phone: " + emp.getPhone());
            System.out.println("Email: " + emp.getEmail());
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void listAllEmployees() {
        List<employee> employees = employeeManager.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employees List:");
            for (employee emp : employees) {
                System.out.println(emp.getEmployee_id() + ": " + emp.getName() + ", " + emp.getEmail());
            }
        }
    }

    private void addReward() {
        System.out.println("Enter Employee ID to add reward:");
        int id = Integer.parseInt(scanner.nextLine());
        employee emp = employeeManager.findEmployeeById(id);

        if (emp != null) {
            System.out.println("Enter Reward Amount:");
            float reward = Float.parseFloat(scanner.nextLine());
            System.out.println("Enter Date (YYYY-MM-DD):");
            String date = scanner.nextLine();

            boolean result = salaryManager.addReward(emp, reward, date);
            System.out.println(result ? "Reward added successfully." : "Failed to add reward.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void applyPenalty() {
        System.out.println("Enter Employee ID to apply penalty:");
        int id = Integer.parseInt(scanner.nextLine());
        employee emp = employeeManager.findEmployeeById(id);

        if (emp != null) {
            System.out.println("Enter Penalty Amount:");
            float penalty = Float.parseFloat(scanner.nextLine());
            System.out.println("Enter Date (YYYY-MM-DD):");
            String date = scanner.nextLine();

            boolean result = salaryManager.applyPenalty(emp, penalty, date);
            System.out.println(result ? "Penalty applied successfully." : "Failed to apply penalty.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void viewTotalNetSalary() {
        System.out.println("Enter Employee ID to view net salary:");
        int id = Integer.parseInt(scanner.nextLine());
        employee emp = employeeManager.findEmployeeById(id);

        if (emp != null) {
            float netSalary = salaryManager.getTotalNetSalary(emp);
            System.out.println("Total Net Salary: " + netSalary);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void listAllSalaries() {
        List<salary> salaries = salaryManager.getAllSalaries();
        if (salaries.isEmpty()) {
            System.out.println("No salaries found.");
        } else {
            System.out.println("Salaries List:");
            for (salary sal : salaries) {
                System.out.println("Salary ID: " + sal.getSalary_id() + ", Employee ID: " + sal.getE().getEmployee_id()
                        + ", Net Salary: " + sal.getNet_salary());
            }
        }
    }

    private void addSalary() {
        System.out.println("Enter Employee ID for salary:");
        int employeeId = Integer.parseInt(scanner.nextLine());
        employee emp = employeeManager.findEmployeeById(employeeId);

        if (emp != null) {
            System.out.println("Enter Wage per Hour:");
            float wagePerHour = Float.parseFloat(scanner.nextLine());
            System.out.println("Enter Hours Worked:");
            int hoursWorked = Integer.parseInt(scanner.nextLine());

            salary newSalary = new salary(0, wagePerHour, hoursWorked, emp);
            boolean salaryAdded = salaryManager.addSalary(newSalary);

            if (salaryAdded) {
                System.out.println("Salary added successfully.");
            } else {
                System.out.println("Failed to add salary.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}
