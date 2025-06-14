import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-menu',
  templateUrl: './employee-menu.component.html',
  standalone: true,
  styleUrls: ['./employee-menu.component.css']
})
export class EmployeeMenuComponent {
  constructor(private router: Router) {}

  // Redirection vers la page d'ajout d'un employé
  navigateToAddEmployee(): void {
    this.router.navigate(['/create-employee']);
  }

  // Redirection vers la liste des employés
  navigateToListEmployees(): void {
    this.router.navigate(['/employees']);
  }
  goToMenu(): void {
    this.router.navigate(['/menu-page']);
  }
}
