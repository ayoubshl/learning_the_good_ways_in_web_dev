package org.gestion.backendspringboot.repository;

import org.gestion.backendspringboot.model.FicheSalaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheSalaireRepository extends JpaRepository<FicheSalaire, Long> {
}
