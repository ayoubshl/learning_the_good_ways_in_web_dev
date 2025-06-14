import { Component } from '@angular/core';
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-salaire-menu',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: `./salaire-menu.component.html`,
  styleUrl: `./salaire-menu.component.css`
})
export class SalaireMenuComponent {
  constructor(private router: Router) {}

  goToMenu(): void {
    this.router.navigate(['/menu-page']);
  }
}
