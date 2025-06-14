package org.gestion.backendspringboot.repository;

import org.gestion.backendspringboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByMatricule(long matricule);
}
