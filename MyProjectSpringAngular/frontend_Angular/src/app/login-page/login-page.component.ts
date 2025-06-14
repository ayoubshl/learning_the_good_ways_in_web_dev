import { Component } from '@angular/core';
import {FormsModule, NgForm} from '@angular/forms';
import { Router } from '@angular/router';
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  standalone: true,
  imports: [
    FormsModule,
    NgClass
  ],
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  isFormSubmited: boolean = false;
  showPassword = false;
  login = { email: '', password: '', rememberMe: false };

  constructor(private router: Router) {}

  /************************** onLoginSubmit *********************************************/
  onLoginSubmit(loginForm: NgForm) {
    if (loginForm.valid) {
      // Vérification des identifiants
      if (this.login.email === 'admin' && this.login.password === 'admin') {
        console.log('Connexion réussie en tant qu\'admin');
        alert('Bienvenue, Admin!');
        this.router.navigate(['/menu-page']); // Redirection vers la page Admin
      } else {
        alert('Identifiants incorrects. Veuillez réessayer.');
      }
    } else {
      this.isFormSubmited = true;
      console.error('Le formulaire de connexion est invalide');
      alert('Veuillez remplir correctement le formulaire.');
    }
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  toggleCheckbox() {
    this.login.rememberMe = !this.login.rememberMe;
  }
}
