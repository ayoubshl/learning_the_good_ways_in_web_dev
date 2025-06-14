import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FicheSalaire } from '../../models/FicheSalaire'; // Use the existing FicheSalaire model

@Component({
  selector: 'app-update-fiche',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './update-fiche.component.html',
  styleUrls: ['./update-fiche.component.css']
})
export class UpdateFicheComponent implements OnInit {

  fiche!: FicheSalaire; // Use the existing FicheSalaire model
  loading: boolean = true; // Loading state to prevent errors

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const nFicheParam = this.route.snapshot.paramMap.get('nfiche');

    // Parse the nFiche parameter to a number
    const nFiche = nFicheParam ? parseInt(nFicheParam, 10) : null;

    if (nFiche) {
      this.fetchFicheDetails(nFiche);
    } else {
      alert('Invalid salary slip ID.');
      this.goBack();
    }
  }

  fetchFicheDetails(nFiche: number): void {
    this.http.get<FicheSalaire>(`http://localhost:8081/api/fiches/${nFiche}`)
      .subscribe({
        next: (response: FicheSalaire) => {
          this.fiche = response; // Populate fiche with the response
          this.loading = false;
        },
        error: (error) => {
          console.error('Error fetching fiche:', error);
          alert('Failed to fetch salary slip details.');
          this.loading = false;
        }
      });
  }

  onSubmit(): void {
    if (!this.fiche) return;

    // Recalculate montantBrut and montantNet before updating
    this.fiche.montantBrut = this.fiche.nbHeures * this.fiche.tauxHoraires;
    this.fiche.montantNet = this.fiche.montantBrut - this.fiche.tax;

    this.http.put<FicheSalaire>(`http://localhost:8081/api/fiches/${this.fiche.nFiche}`, this.fiche)
      .subscribe({
        next: (response: FicheSalaire) => {
          console.log('Fiche updated successfully:', response);
          alert('Salary slip updated successfully!');
          this.router.navigate(['/fiches']); // Navigate back to the list
        },
        error: (error) => {
          console.error('Error updating fiche:', error);
          alert('Failed to update salary slip.');
        }
      });
  }

  goBack(): void {
    this.router.navigate(['/fiches']); // Navigate back to the salary slips list
  }
}
