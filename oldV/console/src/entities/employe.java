package entities;

public class employe {
    private int Id;
    private String nom;
    private String prenom;
    private String adresse;
    private String numTel;

    public employe(String adresse, String nom, String numTel, String prenom) {
        this.adresse = adresse;
        this.nom = nom;
        this.numTel = numTel;
        this.prenom = prenom;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
}
