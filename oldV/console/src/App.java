import DB.employeDB;          // For employee database
import entities.employe;      // For the employee entity
import entities.ficheSalaire; // For the salary entity
import management.*;
import java.util.Scanner; // For employee management functionality

public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            employeManagement empManager = new employeManagement();
            salaryManagement salaryManager = new salaryManagement();
            
            System.out.println("===== Employee and Salary Management System =====");
            int choice;
            
            do {
                // Display menu options
                System.out.println("\nChoose an action:");
                System.out.println("1. Add Employee");
                System.out.println("2. Search Employee by ID");
                System.out.println("3. Search Employee by Name");
                System.out.println("4. Edit Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. List All Employees");
                System.out.println("7. Add Salary Record");
                System.out.println("8. Get Employee Salary");
                System.out.println("9. List All Salaries");
                System.out.println("0. Exit");
                
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1 -> {
                        // Add employee
                        System.out.print("Enter Employee ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine();
                        
                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine();
                        
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();
                        
                        System.out.print("Enter Phone Number: ");
                        String phone = scanner.nextLine();
                        
                        employe newEmp = new employe(address, firstName, phone, lastName);
                        newEmp.setId(id);
                        boolean result = empManager.addEmployer(newEmp);
                        
                        System.out.println(result ? "Employee added successfully!" : "Failed to add employee (possibly already exists).");
                    }
                    case 2 -> {
                        // Search employee by ID
                        System.out.print("Enter Employee ID to search: ");
                        int id = scanner.nextInt();
                        employe emp = empManager.searchEmploye(id);
                        
                        if (emp != null) {
                            System.out.println("Employee Found: " + emp.getNom() + " " + emp.getPrenom());
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }
                    case 3 -> {
                        // Search employee by name
                        System.out.print("Enter Employee Name to search: ");
                        String name = scanner.nextLine();
                        employe emp = empManager.searchEmploye(name);
                        
                        if (emp != null) {
                            System.out.println("Employee Found: ID: " + emp.getId() + ", Name: " + emp.getNom() + " " + emp.getPrenom());
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }
                    case 4 -> {
                        // Edit employee
                        System.out.print("Enter Employee ID to edit: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        employe empToEdit = empManager.searchEmploye(id);
                        if (empToEdit != null) {
                            System.out.print("Enter New First Name: ");
                            String newFirstName = scanner.nextLine();
                            
                            System.out.print("Enter New Last Name: ");
                            String newLastName = scanner.nextLine();
                            
                            System.out.print("Enter New Address: ");
                            String newAddress = scanner.nextLine();
                            
                            System.out.print("Enter New Phone Number: ");
                            String newPhone = scanner.nextLine();
                            
                            empToEdit.setNom(newFirstName);
                            empToEdit.setPrenom(newLastName);
                            empToEdit.setAdresse(newAddress);
                            empToEdit.setNumTel(newPhone);
                            
                            boolean result = empManager.editEmploye(empToEdit);
                            System.out.println(result ? "Employee updated successfully!" : "Failed to update employee.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }
                    case 5 -> {
                        // Delete employee
                        System.out.print("Enter Employee ID to delete: ");
                        int id = scanner.nextInt();
                        employe empToDelete = empManager.searchEmploye(id);
                        
                        if (empToDelete != null) {
                            boolean result = empManager.deleteEmploye(empToDelete);
                            System.out.println(result ? "Employee deleted successfully!" : "Failed to delete employee.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }
                    case 6 -> {
                        // List all employees
                        System.out.println("Listing all employees:");
                        if (employeDB.employeList.isEmpty()) {
                            System.out.println("No employees found.");
                        } else {
                            for (employe emp : employeDB.employeList) {
                                System.out.println("ID: " + emp.getId() + ", Name: " + emp.getNom() + " " + emp.getPrenom() +
                                        ", Address: " + emp.getAdresse() + ", Phone: " + emp.getNumTel());
                            }
                        }
                    }
                    case 7 -> {
                        // Add salary record
                        System.out.print("Enter Employee ID for Salary: ");
                        int empId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        employe emp = empManager.searchEmploye(empId);
                        if (emp != null) {
                            System.out.print("Enter Number of Hours Worked: ");
                            int hoursWorked = scanner.nextInt();
                            System.out.print("Enter Hourly Rate: ");
                            double hourlyRate = scanner.nextDouble();
                            double salaryBrute = hourlyRate * hoursWorked;
                            double salaryNet = salaryBrute * (1 - 0.2); // applying tax
                            
                            ficheSalaire salaryRecord = new ficheSalaire(emp, hoursWorked, salaryBrute, salaryNet, hourlyRate);
                            salaryManager.getSalary(salaryRecord);
                            
                            System.out.println("Salary record added for " + emp.getNom() + " " + emp.getPrenom());
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }
                    case 8 -> {
                        // Get salary
                        System.out.print("Enter Employee ID for Salary: ");
                        int empId = scanner.nextInt();
                        scanner.nextLine();
                        employe emp = empManager.searchEmploye(empId);
                        if (emp != null) {
                            ficheSalaire salary = salaryManagement.getSalary();
                            System.out.println("Salary for " + emp.getNom() + ": " + salary);
                        }
                    }
                    case 9 -> {
                        // List all salaries
                        System.out.println("Listing all salaries...");
                    }
                    case 0 -> System.out.println("Exiting Employee Management System. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);
        }
    }
}
