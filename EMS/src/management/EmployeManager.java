package management;

import Interfaces.IEmployee;
import models.employee;
import DAO.DBemployee;

public class EmployeManager implements IEmployee {

    @Override
    public boolean add_employee(employee employee) {
        return DBemployee.employees_list.add(employee);
    }

    @Override
    public boolean remove_employee(employee employee) {
        return DBemployee.employees_list.remove(employee);
    }

    @Override
    public employee get_employee(int id) {
        return DBemployee.employees_list.get(id);
    }

    @Override
    public employee edit_employee(employee employee) {
        int id = employee.getEmployee_id();
        return DBemployee.employees_list.set(id , employee);

    }
    @Override
    public employee find_Employee_by_id(int id) {
        for (employee e : DBemployee.employees_list) {
            if (e.getEmployee_id() == id) {
                return e;
            }
        }
        return null;
    }

}
