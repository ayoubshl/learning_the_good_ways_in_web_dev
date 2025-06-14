import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Importer CommonModule ici

// Définition de l'interface Employee
interface Employee {
  matricule: string;
  firstName: string;
  lastName: string;
  adress: string;
  email: string;
}

@Component({
  selector: 'app-update-employee',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule // Ajouter CommonModule dans les imports
  ],
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  employee: Employee = {
    matricule: '',
    firstName: '',
    lastName: '',
    adress: '',
    email: ''
  };

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const matricule = this.route.snapshot.paramMap.get('matricule');

    if (matricule) {
      // Utiliser le type Employee pour garantir que la réponse soit conforme à l'interface
      this.http.get<Employee>(`http://localhost:8081/api/employees/${matricule}`)
        .subscribe({
          next: (response: Employee) => {
            this.employee = response;
          },
          error: (error) => {
            console.error('Error fetching employee:', error);
            alert('Failed to fetch employee.');
          }
        });
    }
  }

  onSubmit(): void {
    const matricule = this.employee.matricule;

    this.http.put<Employee>(`http://localhost:8081/api/employees/${matricule}`, this.employee)
      .subscribe({
        next: (response: Employee) => {
          console.log('Employee updated successfully:', response);
          alert('Employee updated successfully!');
          this.router.navigate(['/employees']);
        },
        error: (error) => {
          console.error('Error updating employee:', error);
          alert('Failed to update employee.');
        }
      });
  }
  goBack(): void {
    window.history.back();
  }
}
