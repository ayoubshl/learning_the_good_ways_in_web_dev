package management;

import DB.employeDB;
import entities.employe;
import interfaces.employeInterface;
import java.util.ArrayList;

public class employeManagement implements employeInterface {

    @Override
    public Boolean addEmployer(employe e) {
        ArrayList<employe> employeList = employeDB.employeList;
        if (e == null || employeList.contains(e)) {
            return false;
        }
        try {
            employeList.add(e);
            return true;
        } catch (Exception ex) {
            System.err.println("Error adding employee: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteEmploye(employe e) {
        ArrayList<employe> employeList = employeDB.employeList;
        if (e == null || !employeList.contains(e)) {
            return false;
        }
        try {
            employeList.remove(e);
            return true;
        } catch (Exception ex) {
            System.err.println("Error deleting employee: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Boolean addEmploye(int id) {
        
        return null;
    }

    @Override
    public employe searchEmploye(int id) {
        ArrayList<employe> employeList = employeDB.employeList;
        for (employe e : employeList) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @Override
    public employe searchEmploye(String nom) {
        ArrayList<employe> employeList = employeDB.employeList;
        for (employe e : employeList) {
            if (e.getNom().equalsIgnoreCase(nom)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Boolean editEmploye(employe employe) {
        ArrayList<employe> employeList = employeDB.employeList;
        for (employe e : employeList) {
            if (e.getId() == employe.getId()) {
                e.setNom(employe.getNom());
                e.setPrenom(employe.getPrenom());
                e.setAdresse(employe.getAdresse());
                e.setNumTel(employe.getNumTel());
                return true;
            }
        }
        return false;
    }
}
