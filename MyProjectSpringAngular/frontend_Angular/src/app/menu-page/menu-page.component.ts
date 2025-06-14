import { Component } from '@angular/core';
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-menu-page',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './menu-page.component.html',
  styleUrl: './menu-page.component.css'
})
export class MenuPageComponent {
  constructor(private router: Router) {}

deconnecter(): void {
    // Clear session storage or perform logout logic
    console.log('Déconnexion en cours...');
    alert('Vous avez été déconnecté.');
    this.router.navigate(['/login']); // Navigate to login page
  }
}
