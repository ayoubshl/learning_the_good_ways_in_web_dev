package org.gestion.backendspringboot.controller;

import org.gestion.backendspringboot.model.FicheSalaire;
import org.gestion.backendspringboot.service.FicheSalaireService;
import org.gestion.backendspringboot.service.FicheSalaireServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/fiches")
public class FicheSalaireController {

    private final FicheSalaireService ficheSalaireService;

    public FicheSalaireController(FicheSalaireServiceImpl ficheSalaireServiceImpl) {
        this.ficheSalaireService = ficheSalaireServiceImpl;
    }

    @GetMapping
    public List<FicheSalaire> getAllFiches() {

        return ficheSalaireService.getAllFiches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FicheSalaire> getFicheById(@PathVariable int id) {
        return ficheSalaireService.getFicheById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FicheSalaire createFiche(@RequestBody FicheSalaire ficheSalaire) {
        return ficheSalaireService.saveFiche(ficheSalaire).getBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FicheSalaire> updateFiche(@PathVariable long id, @RequestBody FicheSalaire ficheSalaire) {
        if (ficheSalaireService.getFicheById(id).isPresent()) {
            ficheSalaire.setnFiche(id);
            return ResponseEntity.ok(ficheSalaireService.saveFiche(ficheSalaire).getBody());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiche(@PathVariable long id) {
        if (ficheSalaireService.getFicheById(id).isPresent()) {
            ficheSalaireService.deleteFiche(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
