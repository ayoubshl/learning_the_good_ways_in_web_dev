import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-fiche-salaire',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './create-fiche-salaire.component.html',
  styleUrls: ['./create-fiche-salaire.component.css'] // Corrected styleUrls
})
export class CreateFicheSalaireComponent {
  ficheSalaire = {
    nFiche: 0,
    dateFin: '',
    nbHeures: 0,
    tauxHoraires: 0,
    montantBrut: 0,
    tax: 0,
    montantNet: 0,
    matricule: ''
  };

  private apiUrl = 'http://localhost:8081/api/fiches'; // Backend API URL

  constructor(private http: HttpClient) {}

  onSubmit() {
    // Calcul du montant brut et net
    this.ficheSalaire.montantBrut = this.ficheSalaire.nbHeures * this.ficheSalaire.tauxHoraires;
    this.ficheSalaire.montantNet = this.ficheSalaire.montantBrut - this.ficheSalaire.tax;

    console.log('Form submitted:', this.ficheSalaire);

    // Appel direct à l'API avec HttpClient
    this.http.post(this.apiUrl, this.ficheSalaire)
      .subscribe({
        next: (response) => {
          console.log('Fiche de salaire créée avec succès:', response);
          alert('Fiche de salaire créée avec succès!');
        },
        error: (error) => {
          console.error('Erreur lors de la création de la fiche de salaire:', error);
          alert('Erreur lors de la création de la fiche de salaire.');
        }
      });
  }

  goBack(): void {
    window.history.back();
  }
}
