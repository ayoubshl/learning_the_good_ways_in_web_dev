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
            stmt.setDate(3, Date.valueOf(date));
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
            stmt.setDate(3, Date.valueOf(date));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
