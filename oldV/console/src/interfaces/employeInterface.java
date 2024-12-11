package interfaces;

import entities.employe;

public interface employeInterface {
    Boolean addEmployer(employe e);

    Boolean deleteEmploye(employe e);

    Boolean addEmploye(int id);

    employe searchEmploye(int id);

    employe searchEmploye(String nom);

    Boolean editEmploye(employe employe);
}
