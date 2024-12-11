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
        String sql = "INSERT INTO employees (id, name, address, phone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, emp.getEmployee_id());
            stmt.setString(2, emp.getName());
            stmt.setString(3, emp.getAddress());
            stmt.setString(4, emp.getPhone());
            stmt.setString(5, emp.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
            if (stmt.executeUpdate() > 0) {
                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
