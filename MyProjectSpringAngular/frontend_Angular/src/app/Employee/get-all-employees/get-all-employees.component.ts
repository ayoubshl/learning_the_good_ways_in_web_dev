import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Required for *ngFor and *ngIf
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-gest-all-employees',
  standalone: true,
  templateUrl: './get-all-employees.component.html',
  styleUrls: ['./get-all-employees.component.css'],
  imports: [CommonModule, HttpClientModule, FormsModule] // Add required modules here
})
export class GetAllEmployeesComponent implements OnInit {
  employees: any[] = []; // Liste complète des employés
  filteredEmployees: any[] = []; // Liste filtrée
  searchMatricule: string = ''; // Valeur de recherche

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees(): void {
    this.http.get<any[]>('http://localhost:8081/api/employees')
      .subscribe({
        next: (response) => {
          this.employees = response;
          this.filteredEmployees = response; // Initialiser la liste filtrée
        },
        error: (error) => {
          console.error('Error fetching employees:', error);
          alert('Failed to fetch employees.');
        }
      });
  }

  // Filtrer les employés par matricule
  filterEmployees(): void {
    const searchTerm = this.searchMatricule.toLowerCase();
    this.filteredEmployees = this.employees.filter(employee =>
      employee.matricule.toLowerCase().includes(searchTerm)
    );
  }

  deleteEmployee(matricule: string): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.http.delete(`http://localhost:8081/api/employees/${matricule}`).subscribe({
        next: () => {
          alert('Employee deleted successfully.');
          this.getEmployees(); // Rafraîchir la liste après suppression
        },
        error: (error) => {
          console.error('Error deleting employee:', error);
          alert('Failed to delete employee. Status: ' + error.status); // Affichez le code de statut HTTP
          if (error.error) {
            console.error('Error response:', error.error); // Affichez la réponse détaillée du backend
          }
        }
      });
    }
  }

  updateEmployee(matricule: string): void {
    this.router.navigate([`update-employee`, matricule]); // Pas besoin de "/:matricule" ici
  }

  viewEmployee(matricule: string): void {
    this.router.navigate([`employee-details`, matricule]); // Pas besoin de "/:matricule" ici
  }
  logout(): void {
    this.router.navigate(['/employee-menu']);
  }
}
