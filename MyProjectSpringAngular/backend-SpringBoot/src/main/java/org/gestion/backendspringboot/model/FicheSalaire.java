package org.gestion.backendspringboot.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class FicheSalaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nFiche;
    private LocalDate dateFin;
    private int nbHeures;
    private int tauxHoraires;
    private double montantBrut;
    private double tax;
    private double montantNet;
    @Transient
    private long matricule;
    @PostLoad
    public void setMatriculeFromEmployee() {
        if (this.employe != null) {
            this.matricule = this.employe.getMatricule();
        }
    }
    @ManyToOne
    @JoinColumn(name = "matricule", nullable = false)
    private Employee employe;

    public FicheSalaire() {
    }

    public FicheSalaire(LocalDate dateFin, Employee employe, double montantBrut, double montantNet, int nbHeures, Long nFiche, int tauxHoraires, double tax) {
        this.dateFin = dateFin;
        this.employe = employe;
        this.montantBrut = montantBrut;
        this.montantNet = montantNet;
        this.nbHeures = nbHeures;
        this.nFiche = nFiche;
        this.tauxHoraires = tauxHoraires;
        this.tax = tax;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Employee getEmploye() {
        return employe;
    }

    public void setEmploye(Employee employe) {
        this.employe = employe;
    }

    public double getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(double montantBrut) {
        this.montantBrut = montantBrut;
    }

    public double getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(double montantNet) {
        this.montantNet = montantNet;
    }

    public int getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(int nbHeures) {
        this.nbHeures = nbHeures;
    }

    public Long getnFiche() {
        return nFiche;
    }

    public void setnFiche(Long nFiche) {
        this.nFiche = nFiche;
    }

    public int getTauxHoraires() {
        return tauxHoraires;
    }

    public void setTauxHoraires(int tauxHoraires) {
        this.tauxHoraires = tauxHoraires;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "FicheSalaire{" +
                "dateFin=" + dateFin +
                ", nFiche=" + nFiche +
                ", nbHeures=" + nbHeures +
                ", tauxHoraires=" + tauxHoraires +
                ", montantBrut=" + montantBrut +
                ", tax=" + tax +
                ", montantNet=" + montantNet +
                ", employe=" + employe +
                '}';
    }
    public long getMatricule() {
        return matricule;
    }
}
