export interface FicheSalaire {
  nFiche: number;
  dateFin: string;  // Correspond à LocalDate en Java, il faut envoyer une date au format 'YYYY-MM-DD'
  nbHeures: number;
  tauxHoraires: number;
  montantBrut: number;
montantNet:number;
  tax: number;

  matricule: string;  // Id de l'employé, cela doit correspondre à la relation ManyToOne dans le backend
}
