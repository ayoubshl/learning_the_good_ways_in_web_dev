package ihm;

import java.util.Scanner;
import management.*;
import models.*;

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
            System.out.println("5. Add Reward");
            System.out.println("6. Apply Penalty");
            System.out.println("7. View Total Net Salary");
            System.out.println("8. Exit");

            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> removeEmployee();
                case 3 -> editEmployee();
                case 4 -> viewEmployeeDetails();
                case 5 -> addReward();
                case 6 -> applyPenalty();
                case 7 -> viewTotalNetSalary();
                case 8 -> {
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addEmployee() {
        System.out.println("Enter Employee ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        System.out.println("Enter Phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        employee newEmployee = new employee(id, name, address, phone, email);
        boolean result = employeeManager.addEmployee(newEmployee);
        System.out.println(result ? "Employee added successfully." : "Failed to add employee.");
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
}
