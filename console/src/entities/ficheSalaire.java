package entities;

public class ficheSalaire {
    private employe e;
    private int nbHeure;
    private double tauxHeure;
    private double salaireBrute;
    private double salaireNet;
    private final double tax = 0.2;

   

    public ficheSalaire(employe e, int nbHeure, double salaireBrute, double salaireNet, double tauxHeure) {
        this.e = e;
        this.nbHeure = nbHeure;
        this.salaireBrute = salaireBrute;
        this.salaireNet = salaireNet;
        this.tauxHeure = tauxHeure;
    }

    
    public double getTax() {
        return tax;
    }
    public employe getE() {
        return e;
    }

    public void setE(employe e) {
        this.e = e;
    }

    public int getNbHeure() {
        return nbHeure;
    }

    public void setNbHeure(int nbHeure) {
        this.nbHeure = nbHeure;
    }

    public double getTauxHeure() {
        return tauxHeure;
    }

    public void setTauxHeure(double tauxHeure) {
        this.tauxHeure = tauxHeure;
    }

    public double getSalaireBrute() {
        return salaireBrute;
    }

    public void setSalaireBrute(double salaireBrute) {
        this.salaireBrute = salaireBrute;
    }

    public double getSalaireNet() {
        return salaireNet;
    }

    public void setSalaireNet(double salaireNet) {
        this.salaireNet = salaireNet;
    }
}
