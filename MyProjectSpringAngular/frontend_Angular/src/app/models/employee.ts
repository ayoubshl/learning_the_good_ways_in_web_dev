// src/app/employee.ts (ou src/app/models/employee.ts)
export interface Employee {
  matricule: bigint;
  firstName: string;
  lastName: string;
  adress: string;
  email: string;
  // Ajoutez d'autres champs ici selon la structure de votre table "employee"
}
