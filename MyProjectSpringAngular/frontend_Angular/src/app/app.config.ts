import { routes } from './app.routes'; // Assurez-vous que le chemin est correct
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
// @ts-ignore

@NgModule({
  imports: [RouterModule.forRoot(routes)], // Utilisation de 'routes' ici
  exports: [RouterModule]
})
export class AppRoutingModule {}


export const appConfig = {
  apiUrl: 'http://localhost:8081/api',
  appName: 'Employee Management System',
  providers: []  // Vous ajoutez ici les services que votre application utilise
};
