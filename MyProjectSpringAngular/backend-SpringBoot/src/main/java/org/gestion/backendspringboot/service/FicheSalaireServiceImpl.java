package org.gestion.backendspringboot.service;

import org.gestion.backendspringboot.model.Employee;
import org.gestion.backendspringboot.repository.EmployeeRepository;
import org.gestion.backendspringboot.model.FicheSalaire;
import org.gestion.backendspringboot.repository.FicheSalaireRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FicheSalaireServiceImpl implements FicheSalaireService {

    private final FicheSalaireRepository ficheSalaireRepository;
    private final EmployeeRepository employeeRepository;

    public FicheSalaireServiceImpl(FicheSalaireRepository ficheSalaireRepository, EmployeeRepository employeeRepository) {
        this.ficheSalaireRepository = ficheSalaireRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<FicheSalaire> getAllFiches() {
        return ficheSalaireRepository.findAll();
    }

    @Override
    public Optional<FicheSalaire> getFicheById(long id) {
        return ficheSalaireRepository.findById(id);
    }

    @Override
    public ResponseEntity<FicheSalaire> saveFiche(FicheSalaire ficheSalaire) {
        long mat = ficheSalaire.getMatricule(); // Get matricule from FicheSalaire
        Optional<Employee> employee = employeeRepository.findByMatricule(mat);

        if (employee.isPresent()) {
            // Calculate the salary amounts
            double montantBrut = ficheSalaire.getNbHeures() * ficheSalaire.getTauxHoraires();
            double montantNet = montantBrut * (1 - ficheSalaire.getTax() / 100);
            ficheSalaire.setMontantBrut(montantBrut);
            ficheSalaire.setMontantNet(montantNet);

            // Associate the Employee with FicheSalaire
            ficheSalaire.setEmploye(employee.get());
            return ResponseEntity.ok(ficheSalaireRepository.save(ficheSalaire));
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if Employee is not found
        }
    }


    @Override
    public void deleteFiche(long id) {
        ficheSalaireRepository.deleteById(id);
    }
}
