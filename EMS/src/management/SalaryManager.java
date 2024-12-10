package management;
import DAO.DBsalary;
import Interfaces.ISalary;
import models.employee;
import models.salary;

public class SalaryManager implements ISalary {
    @Override
    public float get_total_net_salary(employee employee) {

        float total = 0;
        for( salary s : DBsalary.salaries_list ){
            if(s.getE().getEmployee_id() == employee.getEmployee_id())
                total += s.getNet_salary();
        }
        return total;
    }

    @Override
    public boolean add_reward(employee employee, float reward , String date) {
        for( salary s : DBsalary.salaries_list ){
            if(s.getE().getEmployee_id() == employee.getEmployee_id() && s.getDate().equals(date) ){
                    s.setNet_salary(s.getNet_salary() + reward);
                    return true;
            }


        }
        return false;
    }

    @Override
    public boolean apply_penalty(employee employee, float penalty , String date) {
        for( salary s : DBsalary.salaries_list ){
            if(s.getE().getEmployee_id() == employee.getEmployee_id() && s.getDate().equals(date) ){
                s.setNet_salary(s.getNet_salary() + penalty);
                return true;
            }


        }
        return false;    }


}
