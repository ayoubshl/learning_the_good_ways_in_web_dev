package IHM;

import java.util.Scanner;

import entities.employe;
import management.employeManagement;

public class IHMemployee {
    static employeManagement gestion = new employeManagement();
    static Scanner scan = new Scanner(System.in);

    public static void sasirEmploye() {
        employe e = new employe();
        System.out.println("Saisir l'Id");
        e.setId(scan.nextInt());
        System.out.println("Saisir le nom : ");
        e.setNom(scan.next());
        System.out.println("Saisir le prenom : ");
        e.setPrenom(scan.next());
        System.out.println("Saisir l'adresse ");
        e.setAdresse(scan.next());
        System.out.println("Saisir le numéro tel ");
        e.setNumTel(scan.next());

        gestion.addEmployer(e);
    }

    public static void afficherEmploye(String nom){

        employe e = gestion.searchEmploye(nom);

        System.out.println("le nom est :" + e.getNom());
        System.out.println("le prenom est :" + e.getPrenom());
        System.out.println("l'adresse est :" + e.getAdresse());
        System.out.println("le num de tel est :" + e.getNumTel());

    }
}
