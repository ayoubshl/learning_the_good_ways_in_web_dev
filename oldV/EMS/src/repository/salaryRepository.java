package repository;

import models.employee;

public interface salaryRepository {
    float getTotalNetSalary(employee emp);          // Calculate the total net salary for an employee
    boolean addReward(employee emp, float reward, String date); // Add a reward to a salary
    boolean applyPenalty(employee emp, float penalty, String date); // Apply a penalty to a salary
}
