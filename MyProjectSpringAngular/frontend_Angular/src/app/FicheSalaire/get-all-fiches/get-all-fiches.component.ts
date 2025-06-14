  import { Component, OnInit } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Router } from '@angular/router';
  import { CommonModule } from '@angular/common'; // Required for Angular directives
  import { HttpClientModule } from '@angular/common/http';
  import { FicheSalaire } from '../../models/FicheSalaire'; // Update with your actual model path

  @Component({
    selector: 'app-get-all-fiches',
    standalone: true,
    imports: [
      CommonModule, // Import CommonModule to enable directives like *ngFor
      HttpClientModule // To use HttpClient
    ],
    templateUrl: './get-all-fiches.component.html',
    styleUrls: ['./get-all-fiches.component.css']
  })
  export class GetAllFichesComponent implements OnInit {
    fiches: FicheSalaire[] = []; // Array to hold all salary slips
    errorMessage: string | null = null; // For error handling
    private apiUrlFiches = 'http://localhost:8081/api/fiches'; // Backend URL for salary slips

    constructor(private http: HttpClient, private router: Router) {}

    ngOnInit(): void {
      this.fetchAllFiches();
    }

    fetchAllFiches(): void {
      this.http.get<FicheSalaire[]>(this.apiUrlFiches).subscribe({
        next: (data: FicheSalaire[]) => {
          console.log('Fetched Fiches:', data); // Log the fetched data
          this.fiches = data;
        },
        error: (error) => {
          console.error('Error fetching fiches:', error);
          this.errorMessage = 'An error occurred while fetching the salary slips.';
        }
      });
    }

    deleteFiche(id: number): void {
      if (confirm('Are you sure you want to delete this salary slip?')) {
        this.http.delete(`${this.apiUrlFiches}/${id}`).subscribe({
          next: () => {
            this.fiches = this.fiches.filter(fiche => fiche.nFiche !== id);
            alert('Salary slip deleted successfully!');
          },
          error: (error) => {
            console.error('Error deleting fiche:', error);
            alert('Failed to delete salary slip.');
          }
        });
      }
    }

    viewFiche(id: number): void {
      this.router.navigate([`/fiche-detail/${id}`]);
    }

    logout(): void {
      this.router.navigate(['/salaire-menu']);
    }
    updateFiche(nfiche: number): void {
      this.router.navigate([`update-fiche`, nfiche]); // Pas besoin de "/:matricule" ici
    }
  }
