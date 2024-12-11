package IHM;

import Enteties.FicheSalaire;
import Services.GestionEmployer;
import Services.GestionSalaire;
import Services.InterfaceGestion;
import Services.InterfaceSalaire;
import entities.ficheSalaire;
import interfaces.salaryInterface;

import java.util.Scanner;

public class IHMsalaire {

    static salaryInterface gestion = new salaryInterface();
    static Scanner scan = new Scanner(System.in);

    public static void saisirFicheSalaire(){
        ficheSalaire f = new ficheSalaire();
        System.out.println("Saisir le nombre h'heures :");
        f.setNbHeure(scan.nextInt());

        System.out.println("Saisir le taux h'heures :");
        f.setTauxHeure(scan.nextDouble());

        gestion.ajouterFicheSalaire(f);

        gestion.calculerSalair(f);


    }

    public static void afficherFicheSalaire(int fId){
        FicheSalaire f = gestion.chercherFicheSalaire(fId);

        System.out.println("Le nombre d'heures est :"+ f.getNbHeure());
        System.out.println("Le taux d'heures est :"+ f.getTauxHeure());
        System.out.println("Le salaire brute est :"+ f.getSalaireBrut());
        System.out.println("Le salaire net est :"+ f.getSalireNet());

    }
}