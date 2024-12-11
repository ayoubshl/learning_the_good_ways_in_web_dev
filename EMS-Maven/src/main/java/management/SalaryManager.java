package management;

import models.employee;
import repository.salaryRepository;

public class SalaryManager {
    private final salaryRepository repository;

    public SalaryManager(salaryRepository repository) {
        this.repository = repository;
    }

    public float getTotalNetSalary(employee emp) {
        return repository.getTotalNetSalary(emp);
    }

    public boolean addReward(employee emp, float reward, String date) {
        return repository.addReward(emp, reward, date);
    }

    public boolean applyPenalty(employee emp, float penalty, String date) {
        return repository.applyPenalty(emp, penalty, date);
    }
}
