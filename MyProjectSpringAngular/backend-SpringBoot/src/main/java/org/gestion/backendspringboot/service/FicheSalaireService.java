package org.gestion.backendspringboot.service;

import org.gestion.backendspringboot.model.FicheSalaire;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface FicheSalaireService {
    List<FicheSalaire> getAllFiches();
    Optional<FicheSalaire> getFicheById(long id);
    ResponseEntity<FicheSalaire> saveFiche(FicheSalaire ficheSalaire);
    void deleteFiche(long id);
}