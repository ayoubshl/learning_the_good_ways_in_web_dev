import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FicheSalaire } from '../../models/FicheSalaire'; // Update with your actual model path

// Define the FicheSalaire interface


@Component({
  selector: 'app-fiche-details',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterModule],
  templateUrl: './fiche-details.component.html',
  styleUrls: ['./fiche-details.component.css']
})
export class FicheDetailsComponent implements OnInit {
  fiche: FicheSalaire | null = null;

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.getFicheDetails(id);
    } else {
      console.error('Fiche ID not found in route.');
      alert('Invalid salary slip ID.');
      this.goBack();
    }
  }

  getFicheDetails(id: string): void {
    this.http.get<FicheSalaire>(`http://localhost:8081/api/fiches/${id}`)
      .subscribe({
        next: (response) => {
          this.fiche = response;
        },
        error: (error) => {
          console.error('Error fetching salary slip details:', error);
          alert('Failed to fetch salary slip details.');
        }
      });
  }

  goBack(): void {
    window.history.back();
  }
}
