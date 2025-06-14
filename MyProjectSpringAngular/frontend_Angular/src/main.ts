import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes'; // Si tu as un fichier de routes séparé

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes) // Fournir les routes ici
  ],
}).catch(err => console.error(err));
