import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app.config'; // Vérifiez que ce chemin est correct
import { AppComponent } from './app.component';
import { GetAllEmployeesComponent } from './Employee/get-all-employees/get-all-employees.component';
import { EmployeeDetailsComponent } from './Employee/employee-details/employee-details.component';
import { UpdateEmployeeComponent } from './Employee/update-employee/update-employee.component';
import { CreateFicheSalaireComponent} from "./FicheSalaire/create-fiche-salaire/create-fiche-salaire.component";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import {SidebarComponent} from "./sidebar/sidebar.component";

@NgModule({
  declarations: [

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    GetAllEmployeesComponent,
    EmployeeDetailsComponent,
    UpdateEmployeeComponent,
    CreateFicheSalaireComponent,
    SidebarComponent
  ],
  providers: [],
  bootstrap: [], // Composant principal
})
export class AppModule {}
