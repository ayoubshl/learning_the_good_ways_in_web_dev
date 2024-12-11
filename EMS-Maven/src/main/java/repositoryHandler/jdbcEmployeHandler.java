package repositoryHandler;

import models.employee;
import repository.employeeRepository;
import DAO.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbcEmployeHandler implements employeeRepository {
    private final Connection connection;

    public jdbcEmployeHandler() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public boolean addEmployee(employee emp) {
        String sql = "INSERT INTO employees (name, address, phone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getAddress());
            stmt.setString(3, emp.getPhone());
            stmt.setString(4, emp.getEmail());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        emp.setEmployee_id(generatedKeys.getInt(1)); // Set the auto-generated ID
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeEmployee(employee emp) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, emp.getEmployee_id());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public employee findEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public employee updateEmployee(employee emp) {
        String sql = "UPDATE employees SET name = ?, address = ?, phone = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getAddress());
            stmt.setString(3, emp.getPhone());
            stmt.setString(4, emp.getEmail());
            stmt.setInt(5, emp.getEmployee_id());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return emp; // Return the updated employee object
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if the update fails
    }


    @Override
    public List<employee> getAllEmployees() {
        List<employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(new employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
