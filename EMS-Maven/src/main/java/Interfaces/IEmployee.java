package Interfaces;

import models.employee;

public interface IEmployee {
    public boolean add_employee(employee employee);
    public boolean remove_employee(employee employee);
    public employee get_employee(int id);
    public employee edit_employee(employee employee);
    public employee find_Employee_by_id(int id);
}
