import { Component, OnInit } from '@angular/core';
import { Employee } from '../../models/employee';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterModule],
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {
  employee: Employee | null = null;

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const matricule = this.route.snapshot.paramMap.get('matricule');

    if (matricule) {
      this.getEmployeeDetails(matricule);
    } else {
      console.error('Matricule not found in route.');
      alert('Invalid employee matricule.');
      this.goBack();
    }
  }

  getEmployeeDetails(matricule: string): void {
    this.http.get<Employee>(`http://localhost:8081/api/employees/${matricule}`)
      .subscribe({
        next: (response) => {
          this.employee = response;
        },
        error: (error) => {
          console.error('Error fetching employee details:', error);
          alert('Failed to fetch employee details.');
        }
      });
  }

  goBack(): void {
    window.history.back();
  }
}
