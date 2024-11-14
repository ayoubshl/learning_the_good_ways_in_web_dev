package management;

import entities.ficheSalaire;
import interfaces.salaryInterface;

public class salaryManagement implements salaryInterface {
    @Override
    public double getSalary(ficheSalaire ficheS) {
        if (ficheS == null) {
            return 0;
        }
        return ficheS.getSalaireNet();
    }
}
