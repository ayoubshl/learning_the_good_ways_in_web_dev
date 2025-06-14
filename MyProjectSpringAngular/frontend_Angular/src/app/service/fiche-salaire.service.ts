
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FicheSalaire} from "../models/FicheSalaire";

@Injectable({
  providedIn: 'root'
})
export class FicheSalaireService {

  private apiUrl = 'http://localhost:8081/api/fiches';  // URL du backend Spring Boot

  constructor(private http: HttpClient) { }

  // Récupérer toutes les fiches de salaire
  getAllFiches(): Observable<FicheSalaire[]> {
    return this.http.get<FicheSalaire[]>(this.apiUrl);
  }

  // Créer une fiche de salaire
  createFiche(ficheSalaire: FicheSalaire): Observable<FicheSalaire> {
    return this.http.post<FicheSalaire>(this.apiUrl, ficheSalaire);
  }

  // Récupérer une fiche de salaire par son ID
  getFicheById(id: number): Observable<FicheSalaire> {
    return this.http.get<FicheSalaire>(`${this.apiUrl}/${id}`);
  }

  // Mettre à jour une fiche de salaire
  updateFiche(id: number, ficheSalaire: FicheSalaire): Observable<FicheSalaire> {
    return this.http.put<FicheSalaire>(`${this.apiUrl}/${id}`, ficheSalaire);
  }

  // Supprimer une fiche de salaire
  deleteFiche(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
