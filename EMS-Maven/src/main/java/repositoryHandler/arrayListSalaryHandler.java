package repositoryHandler;

import models.salary;
import models.employee;
import repository.salaryRepository;

import java.util.ArrayList;
import java.util.List;

public class arrayListSalaryHandler implements salaryRepository {
    private final List<salary> salaries = new ArrayList<>();

    @Override
    public float getTotalNetSalary(employee emp) {
        float total = 0;
        for (salary s : salaries) {
            if (s.getE().getEmployee_id() == emp.getEmployee_id()) {
                total += s.getNet_salary();
            }
        }
        return total;
    }

    @Override
    public boolean addReward(employee emp, float reward, String date) {
        for (salary s : salaries) {
            if (s.getE().getEmployee_id() == emp.getEmployee_id() && s.getDate().equals(date)) {
                s.setNet_salary(s.getNet_salary() + reward);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean applyPenalty(employee emp, float penalty, String date) {
        for (salary s : salaries) {
            if (s.getE().getEmployee_id() == emp.getEmployee_id() && s.getDate().equals(date)) {
                s.setNet_salary(s.getNet_salary() - penalty);
                return true;
            }
        }
        return false;
    }
}
