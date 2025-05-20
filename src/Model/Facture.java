package src.Model;


public class Facture {
    private int id;         // id de la facture
    private int sejourId;   // id de la réservation associée
    private double totalChambres; // montant total des chambres
    private double totalConsommations; // montant total des consommations

    private double montantTotal; // montant total à payer (calculé ou stocké)
    // Constructeur
    public Facture(int id, int sejourId, double totalChambres, double totalConsommations, double montantTotal) {
        this.id = id;
        this.sejourId = sejourId;
        this.totalChambres = totalChambres;
        this.totalConsommations = totalConsommations;
        this.montantTotal = montantTotal;
    }

   
    // Getters
    public int getId() {
        return id;
    }

    public int getSejourId() {
        return sejourId;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSejourId(int sejourId) {
        this.sejourId = sejourId;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
    public double getTotalChambres() {
        return totalChambres;
    }
    public void setTotalChambres(double totalChambres) {
        this.totalChambres = totalChambres;
    }
    public double getTotalConsommations() {
        return totalConsommations;
    }
    public void setTotalConsommations(double totalConsommations) {
        this.totalConsommations = totalConsommations;
    }

    // Tu peux ajouter une méthode pour calculer le montant total en fonction
    // des chambres et des consommations du séjour, si tu as accès aux données ici.
}
