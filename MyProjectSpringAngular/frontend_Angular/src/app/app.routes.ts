import { Routes } from '@angular/router';
import { GetAllEmployeesComponent } from './Employee/get-all-employees/get-all-employees.component';
import { EmployeeDetailsComponent } from './Employee/employee-details/employee-details.component';
import { UpdateEmployeeComponent } from './Employee/update-employee/update-employee.component';
import {AppComponent} from "./app.component";
import {CreateEmployeeComponent} from "./Employee/create-employee/create-employee.component";
import {CreateFicheSalaireComponent} from "./FicheSalaire/create-fiche-salaire/create-fiche-salaire.component";
import {MenuPageComponent} from "./menu-page/menu-page.component";
import {LoginPageComponent} from "./login-page/login-page.component";
import {EmployeeMenuComponent} from "./Employee/employee-menu/employee-menu.component";
import {SalaireMenuComponent} from "./FicheSalaire/salaire-menu/salaire-menu.component";
import {GetAllFichesComponent} from "./FicheSalaire/get-all-fiches/get-all-fiches.component";
import {FicheDetailsComponent} from "./FicheSalaire/fiche-details/fiche-details.component";
import {UpdateFicheComponent} from "./FicheSalaire/update-fiche/update-fiche.component";

export const routes: Routes = [
  { path: 'create-employee', component: CreateEmployeeComponent },
  { path: 'employees', component: GetAllEmployeesComponent },
  { path: 'employee-details/:matricule', component: EmployeeDetailsComponent },
  { path: 'update-employee/:matricule', component: UpdateEmployeeComponent },
  {path: 'menu-page', component: MenuPageComponent},
  {path: 'login-page', component: LoginPageComponent},
  { path: 'employee-menu', component: EmployeeMenuComponent },
  { path: 'salaire-menu', component: SalaireMenuComponent },
  {path: 'createFiche', component: CreateFicheSalaireComponent },
  { path: 'fiches', component: GetAllFichesComponent },
  { path: 'fiche-detail/:id', component: FicheDetailsComponent },
  { path: 'update-fiche/:nfiche', component: UpdateFicheComponent }, // Route for update page

  { path: '', redirectTo: '/login-page', pathMatch: 'full' },
  { path: '**', redirectTo: '/login-page' },

];
