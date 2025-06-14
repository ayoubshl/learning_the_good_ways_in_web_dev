import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {CreateEmployeeComponent} from "./Employee/create-employee/create-employee.component";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {UpdateEmployeeComponent} from "./Employee/update-employee/update-employee.component";
import {CreateFicheSalaireComponent} from "./FicheSalaire/create-fiche-salaire/create-fiche-salaire.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  isSidebarOpen: boolean = false;

  ngOnInit(): void {
    // Initialisation de DataTables et autres configurations
  }

  toggleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }
}
