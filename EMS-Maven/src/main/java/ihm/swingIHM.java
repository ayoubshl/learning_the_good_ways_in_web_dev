package ihm;

import management.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class swingIHM extends JFrame {
    private final EmployeManager employeeManager;
    private final SalaryManager salaryManager;

    public swingIHM(EmployeManager employeeManager, SalaryManager salaryManager) {
        this.employeeManager = employeeManager;
        this.salaryManager = salaryManager;

        // Set up the JFrame
        setTitle("Employee & Salary Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main menu panel
        JPanel mainMenuPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton manageEmployeesButton = new JButton("Manage Employees");
        JButton manageSalariesButton = new JButton("Manage Salaries");

        mainMenuPanel.add(manageEmployeesButton);
        mainMenuPanel.add(manageSalariesButton);
        add(mainMenuPanel, BorderLayout.CENTER);

        manageEmployeesButton.addActionListener(e -> openEmployeeManagementPanel());
        manageSalariesButton.addActionListener(e -> openSalaryManagementPanel());
    }

    private void openEmployeeManagementPanel() {
        JFrame employeeFrame = new JFrame("Employee Management");
        employeeFrame.setSize(600, 400);
        employeeFrame.setLayout(new GridLayout(5, 1, 10, 10));

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton viewEmployeesButton = new JButton("View Employees");
        JButton editEmployeeButton = new JButton("Edit Employee");
        JButton removeEmployeeButton = new JButton("Remove Employee");
        JButton backButton = new JButton("Back");

        employeeFrame.add(addEmployeeButton);
        employeeFrame.add(viewEmployeesButton);
        employeeFrame.add(editEmployeeButton);
        employeeFrame.add(removeEmployeeButton);
        employeeFrame.add(backButton);

        addEmployeeButton.addActionListener(e -> openAddEmployeeDialog());
        viewEmployeesButton.addActionListener(e -> displayAllEmployees());
        editEmployeeButton.addActionListener(e -> openEditEmployeeDialog());
        removeEmployeeButton.addActionListener(e -> openRemoveEmployeeDialog());
        backButton.addActionListener(e -> employeeFrame.dispose());

        employeeFrame.setVisible(true);
    }

    private void openSalaryManagementPanel() {
        JFrame salaryFrame = new JFrame("Salary Management");
        salaryFrame.setSize(600, 400);
        salaryFrame.setLayout(new GridLayout(4, 1, 10, 10));

        JButton addSalaryButton = new JButton("Add Salary");
        JButton viewSalariesButton = new JButton("View Salaries");
        JButton applyRewardButton = new JButton("Add Reward");
        JButton applyPenaltyButton = new JButton("Apply Penalty");

        salaryFrame.add(addSalaryButton);
        salaryFrame.add(viewSalariesButton);
        salaryFrame.add(applyRewardButton);
        salaryFrame.add(applyPenaltyButton);

        addSalaryButton.addActionListener(e -> openAddSalaryDialog());
        viewSalariesButton.addActionListener(e -> displayAllSalaries());
        applyRewardButton.addActionListener(e -> openAddRewardDialog());
        applyPenaltyButton.addActionListener(e -> openApplyPenaltyDialog());

        salaryFrame.setVisible(true);
    }

    private void openAddEmployeeDialog() {
        JFrame addEmployeeFrame = new JFrame("Add Employee");
        addEmployeeFrame.setSize(400, 400);
        addEmployeeFrame.setLayout(new GridLayout(7, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField wageField = new JTextField();
        JTextField hoursWorkedField = new JTextField();

        JButton submitButton = new JButton("Add Employee");

        addEmployeeFrame.add(new JLabel("Name:"));
        addEmployeeFrame.add(nameField);
        addEmployeeFrame.add(new JLabel("Address:"));
        addEmployeeFrame.add(addressField);
        addEmployeeFrame.add(new JLabel("Phone:"));
        addEmployeeFrame.add(phoneField);
        addEmployeeFrame.add(new JLabel("Email:"));
        addEmployeeFrame.add(emailField);
        addEmployeeFrame.add(new JLabel("Wage per Hour:"));
        addEmployeeFrame.add(wageField);
        addEmployeeFrame.add(new JLabel("Hours Worked:"));
        addEmployeeFrame.add(hoursWorkedField);
        addEmployeeFrame.add(submitButton);

        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all employee details.");
                    return;
                }

                employee newEmployee = new employee(0, name, address, phone, email);
                boolean added = employeeManager.addEmployee(newEmployee);

                if (!added) {
                    JOptionPane.showMessageDialog(this, "Failed to add employee.");
                    return;
                }

                float wagePerHour = Float.parseFloat(wageField.getText());
                int hoursWorked = Integer.parseInt(hoursWorkedField.getText());

                if (wagePerHour <= 0 || hoursWorked <= 0) {
                    JOptionPane.showMessageDialog(this, "Wage and hours worked must be positive numbers.");
                    return;
                }

                salary newSalary = new salary(0, wagePerHour, hoursWorked, newEmployee);
                newSalary.setDate(java.time.LocalDate.now().toString());
                boolean salaryAdded = salaryManager.addSalary(newSalary);

                if (salaryAdded) {
                    JOptionPane.showMessageDialog(this, "Employee and Salary added successfully.");
                    addEmployeeFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add salary.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Ensure wage and hours are correct numbers.");
            }
        });

        addEmployeeFrame.setVisible(true);
    }
    private void displayAllEmployees() {
        List<employee> employees = employeeManager.getAllEmployees();
        JFrame displayFrame = new JFrame("Employee List");
        displayFrame.setSize(600, 400);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        if (employees.isEmpty()) {
            textArea.append("No employees found.\n");
        } else {
            for (employee emp : employees) {
                textArea.append("ID: " + emp.getEmployee_id() + "\n");
                textArea.append("Name: " + emp.getName() + "\n");
                textArea.append("Address: " + emp.getAddress() + "\n");
                textArea.append("Phone: " + emp.getPhone() + "\n");
                textArea.append("Email: " + emp.getEmail() + "\n");
                textArea.append("-------------------------------------\n");
            }
        }

        displayFrame.add(new JScrollPane(textArea));
        displayFrame.setVisible(true);
    }


    private void openEditEmployeeDialog() {
        JFrame editEmployeeFrame = new JFrame("Edit Employee");
        editEmployeeFrame.setSize(400, 300);
        editEmployeeFrame.setLayout(new GridLayout(7, 2, 10, 10));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JButton fetchButton = new JButton("Fetch");
        JButton saveButton = new JButton("Save");

        editEmployeeFrame.add(new JLabel("Employee ID:"));
        editEmployeeFrame.add(idField);
        editEmployeeFrame.add(new JLabel("New Name:"));
        editEmployeeFrame.add(nameField);
        editEmployeeFrame.add(new JLabel("New Address:"));
        editEmployeeFrame.add(addressField);
        editEmployeeFrame.add(new JLabel("New Phone:"));
        editEmployeeFrame.add(phoneField);
        editEmployeeFrame.add(new JLabel("New Email:"));
        editEmployeeFrame.add(emailField);
        editEmployeeFrame.add(fetchButton);
        editEmployeeFrame.add(saveButton);

        fetchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                employee emp = employeeManager.findEmployeeById(id);

                if (emp != null) {
                    nameField.setText(emp.getName());
                    addressField.setText(emp.getAddress());
                    phoneField.setText(emp.getPhone());
                    emailField.setText(emp.getEmail());
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format.");
            }
        });

        saveButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String newName = nameField.getText();
                String newAddress = addressField.getText();
                String newPhone = phoneField.getText();
                String newEmail = emailField.getText();

                employee emp = new employee(id, newName, newAddress, newPhone, newEmail);
                employee updatedEmployee = employeeManager.editEmployee(emp);

                if (updatedEmployee != null) {
                    JOptionPane.showMessageDialog(this, "Employee updated successfully.");
                    editEmployeeFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update employee.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        editEmployeeFrame.setVisible(true);
    }

    private void openRemoveEmployeeDialog() {
        JFrame removeEmployeeFrame = new JFrame("Remove Employee");
        removeEmployeeFrame.setSize(300, 200);
        removeEmployeeFrame.setLayout(new GridLayout(2, 2, 10, 10));

        JTextField idField = new JTextField();
        JButton removeButton = new JButton("Remove Employee");

        removeEmployeeFrame.add(new JLabel("Employee ID:"));
        removeEmployeeFrame.add(idField);
        removeEmployeeFrame.add(removeButton);

        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                employee emp = employeeManager.findEmployeeById(id);
                if (emp != null) {
                    boolean removed = employeeManager.removeEmployee(emp);
                    if (removed) {
                        JOptionPane.showMessageDialog(this, "Employee removed successfully.");
                        removeEmployeeFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to remove employee.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format.");
            }
        });

        removeEmployeeFrame.setVisible(true);
    }

    private void openAddSalaryDialog() {
        JFrame addSalaryFrame = new JFrame("Add Salary");
        addSalaryFrame.setSize(400, 300);
        addSalaryFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField employeeIdField = new JTextField();
        JTextField wageField = new JTextField();
        JTextField hoursWorkedField = new JTextField();
        JButton addButton = new JButton("Add Salary");

        addSalaryFrame.add(new JLabel("Employee ID:"));
        addSalaryFrame.add(employeeIdField);
        addSalaryFrame.add(new JLabel("Wage per Hour:"));
        addSalaryFrame.add(wageField);
        addSalaryFrame.add(new JLabel("Hours Worked:"));
        addSalaryFrame.add(hoursWorkedField);
        addSalaryFrame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIdField.getText());
                float wage = Float.parseFloat(wageField.getText());
                int hoursWorked = Integer.parseInt(hoursWorkedField.getText());

                employee emp = employeeManager.findEmployeeById(employeeId);
                if (emp != null) {
                    salary newSalary = new salary(0, wage, hoursWorked, emp);
                    boolean added = salaryManager.addSalary(newSalary);
                    if (added) {
                        JOptionPane.showMessageDialog(this, "Salary added successfully.");
                        addSalaryFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add salary.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        addSalaryFrame.setVisible(true);
    }

    private void displayAllSalaries() {
        List<salary> salaries = salaryManager.getAllSalaries();
        JFrame displayFrame = new JFrame("Salary List");
        displayFrame.setSize(600, 400);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        if (salaries.isEmpty()) {
            textArea.append("No salaries found.\n");
        } else {
            for (salary sal : salaries) {
                textArea.append("Salary ID: " + sal.getSalary_id() + "\n");
                textArea.append("Employee ID: " + sal.getE().getEmployee_id() + "\n");
                textArea.append("Employee Name: " + sal.getE().getName() + "\n");
                textArea.append("Date: " + sal.getDate() + "\n");
                textArea.append("Hours Worked: " + sal.getHours() + "\n");
                textArea.append("Wage per Hour: " + sal.getTax_per_hour() + "\n");
                textArea.append("Gross Salary: " + sal.getGross_salary() + "\n");
                textArea.append("Net Salary: " + sal.getNet_salary() + "\n");
                textArea.append("-------------------------------------\n");
            }
        }

        displayFrame.add(new JScrollPane(textArea));
        displayFrame.setVisible(true);
    }


    private void openAddRewardDialog() {
        JFrame rewardFrame = new JFrame("Add Reward");
        rewardFrame.setSize(400, 200);
        rewardFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField employeeIdField = new JTextField();
        JTextField rewardField = new JTextField();
        JTextField dateField = new JTextField();
        JButton addRewardButton = new JButton("Add Reward");

        rewardFrame.add(new JLabel("Employee ID:"));
        rewardFrame.add(employeeIdField);
        rewardFrame.add(new JLabel("Reward Amount:"));
        rewardFrame.add(rewardField);
        rewardFrame.add(new JLabel("Date (YYYY-MM-DD):"));
        rewardFrame.add(dateField);
        rewardFrame.add(addRewardButton);

        addRewardButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(employeeIdField.getText());
                float reward = Float.parseFloat(rewardField.getText());
                String date = dateField.getText();

                employee emp = employeeManager.findEmployeeById(id);
                if (emp != null) {
                    boolean result = salaryManager.addReward(emp, reward, date);
                    JOptionPane.showMessageDialog(this, result ? "Reward added successfully." : "Failed to add reward.");
                    rewardFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        rewardFrame.setVisible(true);
    }

    private void openApplyPenaltyDialog() {
        JFrame penaltyFrame = new JFrame("Apply Penalty");
        penaltyFrame.setSize(400, 200);
        penaltyFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField employeeIdField = new JTextField();
        JTextField penaltyField = new JTextField();
        JTextField dateField = new JTextField();
        JButton applyPenaltyButton = new JButton("Apply Penalty");

        penaltyFrame.add(new JLabel("Employee ID:"));
        penaltyFrame.add(employeeIdField);
        penaltyFrame.add(new JLabel("Penalty Amount:"));
        penaltyFrame.add(penaltyField);
        penaltyFrame.add(new JLabel("Date (YYYY-MM-DD):"));
        penaltyFrame.add(dateField);
        penaltyFrame.add(applyPenaltyButton);

        applyPenaltyButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(employeeIdField.getText());
                float penalty = Float.parseFloat(penaltyField.getText());
                String date = dateField.getText();

                employee emp = employeeManager.findEmployeeById(id);
                if (emp != null) {
                    boolean result = salaryManager.applyPenalty(emp, penalty, date);
                    JOptionPane.showMessageDialog(this, result ? "Penalty applied successfully." : "Failed to apply penalty.");
                    penaltyFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        penaltyFrame.setVisible(true);
    }

}
