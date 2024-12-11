package repository;

import models.salary;
import models.employee;
import java.util.List;

public interface salaryRepository {
    boolean addSalary(salary salary);              // Add a new salary record
    float getTotalNetSalary(employee emp);         // Calculate the total net salary for an employee
    boolean addReward(employee emp, float reward, String date); // Add a reward to a salary
    boolean applyPenalty(employee emp, float penalty, String date); // Apply a penalty to a salary
    List<salary> getAllSalaries();                // Get a list of all salary records
}
