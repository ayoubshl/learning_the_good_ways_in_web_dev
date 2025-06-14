import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

class Employee {
}

@Component({
  selector: 'app-create-employee',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css'
})
export class CreateEmployeeComponent {
  employee = {
    firstName: '',
    lastName: '',
    adress: '',
    email: '',
  };

  constructor(private http: HttpClient) {}

  onSubmit() {
    console.log('Form submitted:', this.employee);  // Vérifiez les données
    this.http.post('http://localhost:8081/api/employees', this.employee)
      .subscribe({
        next: (response) => {
          console.log('Employee created successfully:', response);
          alert('Employee created successfully!');
        },
        error: (error) => {
          console.error('Error creating employee:', error);
          alert('Failed to create employee.');
        }
      });
  }
  goBack(): void {
    window.history.back();
  }

}
