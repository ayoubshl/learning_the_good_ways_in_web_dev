import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../models/employee';  // Remplacez par le modèle de votre Employee

@Injectable({
  providedIn: 'root',  // Permet d'injecter ce service dans toute l'application
})
export class EmployeeService {

  private apiUrl = 'http://localhost:8081/api/employees';  // L'URL de votre backend

  constructor(private http: HttpClient) { }

  // Obtenir la liste des employés
  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiUrl);
  }

  // Créer un nouvel employé
  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.apiUrl, employee);
  }

  // Obtenir un employé par son matricule
  getEmployeeById(matricule: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/${matricule}`);
  }

  // Mettre à jour un employé
  updateEmployee(matricule: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiUrl}/${matricule}`, employee);
  }

  // Supprimer un employé
  deleteEmployee(matricule: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${matricule}`);
  }
}
