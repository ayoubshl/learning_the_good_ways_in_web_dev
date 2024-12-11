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
        addEmployeeFrame.setSize(400, 300);
        addEmployeeFrame.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JButton submitButton = new JButton("Add Employee");

        addEmployeeFrame.add(new JLabel("Name:"));
        addEmployeeFrame.add(nameField);
        addEmployeeFrame.add(new JLabel("Address:"));
        addEmployeeFrame.add(addressField);
        addEmployeeFrame.add(new JLabel("Phone:"));
        addEmployeeFrame.add(phoneField);
        addEmployeeFrame.add(new JLabel("Email:"));
        addEmployeeFrame.add(emailField);
        addEmployeeFrame.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            employee newEmployee = new employee(0, name, address, phone, email);
            boolean added = employeeManager.addEmployee(newEmployee);

            if (added) {
                JOptionPane.showMessageDialog(this, "Employee added successfully.");
                addEmployeeFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add employee.");
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

        for (employee emp : employees) {
            textArea.append(emp.getEmployee_id() + " - " + emp.getName() + "\n");
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
        });

        removeEmployeeFrame.setVisible(true);
    }

    private void openAddSalaryDialog() {
        // Implement dialog to add salary for an employee
    }

    private void displayAllSalaries() {
        List<salary> salaries = salaryManager.getAllSalaries();
        JFrame displayFrame = new JFrame("Salary List");
        displayFrame.setSize(600, 400);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (salary sal : salaries) {
            textArea.append("Salary ID: " + sal.getSalary_id() + ", Employee ID: " + sal.getE().getEmployee_id()
                    + ", Net Salary: " + sal.getNet_salary() + "\n");
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
            int id = Integer.parseInt(employeeIdField.getText());
            employee emp = employeeManager.findEmployeeById(id);

            if (emp != null) {
                float reward = Float.parseFloat(rewardField.getText());
                String date = dateField.getText();
                boolean result = salaryManager.addReward(emp, reward, date);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Reward added successfully.");
                    rewardFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add reward.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.");
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
            int id = Integer.parseInt(employeeIdField.getText());
            employee emp = employeeManager.findEmployeeById(id);

            if (emp != null) {
                float penalty = Float.parseFloat(penaltyField.getText());
                String date = dateField.getText();
                boolean result = salaryManager.applyPenalty(emp, penalty, date);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Penalty applied successfully.");
                    penaltyFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to apply penalty.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.");
            }
        });

        penaltyFrame.setVisible(true);
    }
}
