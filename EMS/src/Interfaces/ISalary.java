package Interfaces;

import models.employee;

public interface ISalary {
    public float get_total_net_salary(employee employee);
    public boolean add_reward(employee employee , float reward , String date);
    public boolean apply_penalty(employee employee , float penalty , String date);
}
