package repositoryHandler;

import models.salary;
import models.employee;
import repository.salaryRepository;
import DAO.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbcSalaryHandler implements salaryRepository {
    private final Connection connection;

    public jdbcSalaryHandler() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public boolean addSalary(salary salary) {
        String sql = "INSERT INTO salaries (employee_id, date, hours_worked, wage_per_hour, gross_salary, net_salary) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, salary.getE().getEmployee_id());
            stmt.setString(2, salary.getDate());
            stmt.setInt(3, salary.getHours());
            stmt.setFloat(4, salary.getTax_per_hour());
            stmt.setFloat(5, salary.getGross_salary());
            stmt.setFloat(6, salary.getNet_salary());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        salary.setSalary_id(generatedKeys.getInt(1)); // Set auto-generated ID
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
    public float getTotalNetSalary(employee emp) {
        String sql = "SELECT SUM(net_salary) AS total FROM salaries WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, emp.getEmployee_id());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getFloat("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean addReward(employee emp, float reward, String date) {
        String sql = "UPDATE salaries SET net_salary = net_salary + ? WHERE employee_id = ? AND date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, reward);
            stmt.setInt(2, emp.getEmployee_id());
            stmt.setString(3, date);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean applyPenalty(employee emp, float penalty, String date) {
        String sql = "UPDATE salaries SET net_salary = net_salary - ? WHERE employee_id = ? AND date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, penalty);
            stmt.setInt(2, emp.getEmployee_id());
            stmt.setString(3, date);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<salary> getAllSalaries() {
        List<salary> salaries = new ArrayList<>();
        String sql = "SELECT * FROM salaries";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                salaries.add(new salary(
                        rs.getInt("id"),
                        rs.getFloat("wage_per_hour"),
                        rs.getInt("hours_worked"),
                        null // Assuming the `employee` object is not needed here
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaries;
    }
}
