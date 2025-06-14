package org.gestion.backendspringboot.controller;

import org.gestion.backendspringboot.model.Employee;
import org.gestion.backendspringboot.service.EmployeeService;
import org.gestion.backendspringboot.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeService = employeeServiceImpl;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int matricule) {
        return employeeService.getEmployeeById(matricule)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long matricule, @RequestBody Employee employee) {
        if (employeeService.getEmployeeById(matricule).isPresent()) {
            employee.setMatricule(matricule);
            return ResponseEntity.ok(employeeService.saveEmployee(employee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long matricule) {
        try {
            if (employeeService.getEmployeeById(matricule).isPresent()) {
                employeeService.deleteEmployee(matricule);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Pour afficher l'exception dans les logs du serveur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }}}
